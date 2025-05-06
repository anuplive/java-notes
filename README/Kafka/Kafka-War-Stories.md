Prompt:
### **Production Issue Investigation: Kafka Consumer Lags in a Legacy Struts 2.x System**

#### **System Characteristics**
1. **Tech Stack**:
   - Struts 2.x (non-Spring, thread-safety considerations apply)
   - Apache Tomcat (one "Supplier" instance + multiple "Buyer" instances)
   - Kafka for bidirectional sync (SQL statements as messages)
   - JDBC (HikariCP) → SQL Server (Supplier DB = shared, Buyer DBs = dedicated)

2. **Data Flow**:
   - **Supplier → Buyer**: Kafka messages contain SQL statements; each Buyer’s consumer group filters messages intended for it.
   - **Buyer → Supplier**: All messages are persisted unconditionally on the Supplier side.

#### **Your Goal**
Describe a **production issue centered around Kafka consumer lags** in this system, including:

#### **Response Format**
1. **Technical Narrative (War Story)**
   - Set the scene: Observed symptoms (e.g., Buyer DBs falling out of sync, Kafka lag metrics spiking).
   - Legacy quirks: Struts 2.x thread-model pitfalls, JDBC connection leaks, or Kafka consumer misconfigurations.

2. **Diagnostic Steps**
   - **Kafka Diagnostics**:
     - `kafka-consumer-groups.sh` lag analysis, partition distribution skew.
     - Broker-side metrics (e.g., `NetworkThreadBlockTime`).
   - **JVM/Thread Analysis**:
     - `jstack` dumps to identify thread contention (e.g., Struts actions blocking consumer threads).
     - HikariCP logs (`leakDetectionThreshold` violations).
   - **Database**:
     - SQL Server DMVs (`sys.dm_exec_requests`, `sys.dm_os_wait_stats`) for blocking queries.

3. **Solution**
   - **Code/Config Snippets**:
     - Struts 2.x action fixes (e.g., `@Scope("prototype")` for thread safety).
     - Kafka consumer tuning (`max.poll.records`, `session.timeout.ms`).
     - HikariCP settings (`connectionTimeout`, `maxLifetime`).
   - **Workarounds**:
     - Dynamic partition reassignment to balance load.

4. **Lessons Learned**
   - Architectural: Tight coupling via SQL statements in messages → idempotency risks.
   - Legacy Gotchas: Struts 2.x singletons vs. Kafka consumer thread safety.

5. **Tone**:
   - Conversational but deeply technical (e.g., *"Turns out, the Struts `ActionContext` was holding onto a Hikari connection longer than we thought…"*).
  - Include as many details as possible , there should be flow to the analysis. Each step should be justified

#### **Key Focus Areas**
- **Legacy Quirks**: Struts 2.x thread-local hell, JDBC pool tuning in a Tomcat-heavy environment.
- **Creative Fixes**: Leveraging Kafka’s `isolation.level` to mitigate dirty reads without a full rewrite.

---

# **The Case of the Lagging Kafka Consumer in a Struts 2 Legacy Monolith**

## **The War Story**

Our legacy Java system—a Struts 2.x monolith running on Tomcat—had been humming along for years until Kafka was introduced for asynchronous order processing. Suddenly, we noticed **Kafka consumer lag spiking during peak hours**, causing delays in order fulfillment. The system had:

- **Struts 2.5** (with its infamous thread-safety quirks)
- **Kafka consumers** (using the older `kafka-clients` 2.4)
- **JDBC (HikariCP)** talking to **SQL Server**
- **Tomcat 8.5** with a fixed-size thread pool

At first, we blamed Kafka—but the real culprit was a **deadly cocktail of Struts thread leaks, SQL Server lock contention, and misconfigured HikariCP**.

---

## **Diagnostic Steps: Unraveling the Mess**

### **1. Kafka Consumer Lag Analysis**
**Tool Used:** `kafka-consumer-groups.sh`
```bash
./kafka-consumer-groups.sh --bootstrap-server kafka-broker:9092 --describe --group order-processing-group
```
- **Observation:** Some partitions had **constant lag**, while others processed fine.
- **Hypothesis:** Consumer threads were getting stuck, not just slow.

### **2. Thread Dump Forensics (`jstack`)**
**Tool Used:** `jstack` on the Tomcat PID
```bash
jstack -l <tomcat_pid> > thread_dump.txt
```
- **Key Finding:**
  - Multiple threads blocked in `Struts2 ActionContext` cleanup.
  - Several **Kafka consumer threads stuck in `HikariCP.getConnection()`**.

### **3. SQL Server Lock Contention (DMVs)**
**Tool Used:** SQL Server DMVs
```sql
SELECT 
    t.session_id,
    t.blocking_session_id,
    t.wait_type,
    t.wait_time,
    t.wait_resource,
    sqltext.text AS blocking_sql
FROM sys.dm_exec_requests t
CROSS APPLY sys.dm_exec_sql_text(t.sql_handle) sqltext
WHERE t.blocking_session_id != 0;
```
- **Observation:** Long-running `SELECT` queries with `LCK_M_S` waits blocking Kafka consumer transactions.

### **4. HikariCP Logs**
Enabled debug logging in `log4j.properties`:
```properties
log4j.logger.com.zaxxer.hikari=DEBUG
```
- **Finding:**
  - **Connection acquisition timeout** (`connectionTimeout=30000ms`) was too aggressive.
  - Some Kafka consumer threads starved for connections.

---

## **The Root Cause(s)**

1. **Struts 2.x Thread-Local Leaks**
   - Struts `ActionContext` uses `ThreadLocal` and doesn’t always clean up properly.
   - Over time, threads in Tomcat’s pool became contaminated, leading to memory leaks and stalled Kafka consumers.

2. **SQL Server Lock Contention**
   - A legacy `ORDER_PROCESSING` table had **page locks** due to missing indexes.
   - Kafka consumer transactions were blocked by slow Struts actions.

3. **HikariCP Misconfiguration**
   - `maxPoolSize=10` was too low for concurrent Kafka consumers + HTTP requests.

---

## **The Fixes**

### **1. Struts 2.x Thread Cleanup**
Added a **Servlet Filter** to enforce `ActionContext` cleanup:
```java
public class StrutsCleanupFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
        try {
            chain.doFilter(req, res);
        } finally {
            ActionContext.clear(); // Critical for thread safety!
        }
    }
}
```
**Bonus:** Upgraded to Struts 2.5.30 (fixed some known `ThreadLocal` leaks).

### **2. Kafka Consumer Tuning**
Modified `consumer.properties`:
```properties
max.poll.interval.ms=300000  # Default was 5m; extended due to DB delays
session.timeout.ms=10000     # Lowered to detect failures faster
fetch.max.wait.ms=500       # Reduced to avoid batching delays
```
Also, **moved to manual commits** for better control:
```java
consumer.poll(Duration.ofMillis(100)).forEach(record -> {
    try {
        processOrder(record.value());
        consumer.commitSync();  // Explicit commit
    } catch (Exception e) {
        log.error("Failed record: " + record.offset(), e);
    }
});
```

### **3. SQL Server & HikariCP Fixes**
- **Added missing indexes** on `ORDER_PROCESSING` to reduce locking.
- **Tuned HikariCP**:
  ```java
  HikariConfig config = new HikariConfig();
  config.setMaximumPoolSize(25);  // Increased from 10
  config.setConnectionTimeout(10000);  // Reduced from 30s
  config.setLeakDetectionThreshold(5000);  // Detect leaked connections
  ```

---

## **Lessons Learned**

1. **Legacy Struts + Kafka is a Minefield**
   - Struts `ThreadLocal` leaks can silently kill async processing.
   - **Always enforce cleanup in filters.**

2. **Kafka Consumers Need Defense Against Slow Processing**
   - **Never rely on auto-commit** if DB operations are involved.
   - **Tune `max.poll.interval.ms`** based on worst-case DB latency.

3. **HikariCP + SQL Server Requires Careful Tuning**
   - **Monitor `wait_time` in DMVs** for lock contention.
   - **Set `leakDetectionThreshold`** to catch bad connection handling.

4. **Legacy Systems Need Incremental Modernization**
   - **Struts → Spring Boot** migration was eventually prioritized.
   - **Kafka consumers moved to a separate service** to isolate them from HTTP thread pool issues.

---
Certainly! Let’s dive into further details and additional diagnostics that could help ensure the system remains stable and optimize the Kafka consumer experience.

### **1. Investigating Consumer Group and Topic Configuration Further**

#### **Consumer Group Lag Analysis**
- **Command**:
    ```sh
    kafka-consumer-groups.sh --describe --group inventory-consumer-group --verbose
    ```
    **Explanation**: This command will give a **detailed view** of consumer group lag, which shows how far behind each consumer is in terms of messages. It’s essential to not only track the consumer lag but also how **evenly distributed** the lag is across all partitions.

  **Why it's important**: Large discrepancies in lag across consumers indicate that certain consumers are falling behind, which may signal resource bottlenecks or configuration issues, such as inadequate CPU, memory, or I/O capacity.

#### **Kafka Broker Metrics**
- **Metrics to watch**:
    - `under-replicated-partitions`: This metric indicates if any partitions have fewer replicas than expected, which could cause **data unavailability** or **consumer rebalancing issues** due to lost partition leaders or broker failures.
    - `activeControllerCount`: If the controller isn't active, the Kafka cluster may not be responding properly to partition reassignments or consumer requests.

  **Why it's important**: Ensuring that no partitions are under-replicated helps avoid delays in partition leader election, which can contribute to rebalancing delays or consumer unavailability. Monitoring broker metrics like `activeControllerCount` ensures that the Kafka controller is healthy and can handle metadata updates efficiently.

### **2. Further Kafka Consumer and Producer Configuration Review**

#### **Consumer Configuration Tuning**
- **Config Parameters**:
    - `fetch.max.wait.ms`: This setting determines how long the consumer will wait for new messages before returning. If set too high, the consumer might delay processing unnecessarily.
    - `max.poll.interval.ms`: The maximum time between two consecutive poll operations. A mismatch between `max.poll.records` and this value can lead to **consumer timeout**.
    - `fetch.min.bytes`: Ensures the consumer receives a minimum amount of data in a poll. This prevents excessive network usage and ensures that consumers process messages in batches.

  **Why it's important**: Fine-tuning these configurations ensures that **data consumption** is efficient, and consumers don't encounter timeouts due to **long fetch times** or excessive batch sizes.

#### **Consumer Group Session and Heartbeat Settings**
- **Command**: Adjust and monitor `session.timeout.ms` and `heartbeat.interval.ms` based on actual consumer performance:
    ```properties
    session.timeout.ms=30000
    heartbeat.interval.ms=10000
    ```

  **Why it's important**: If the session timeout is too short, Kafka will assume that the consumer has failed when it’s still processing. Heartbeat intervals should be optimized to ensure that Kafka keeps the consumer alive even under high load conditions.

### **3. Kafka Broker Logs and Monitoring**

#### **Broker Log Monitoring**
- **Look for the following in broker logs**:
    - `partition leader election` issues.
    - `Metadata update failure`.
    - `Connection timeouts`.

  **Why it's important**: If the brokers are having trouble with metadata updates or connection timeouts, the consumers may have difficulty keeping track of the latest partition leader, which is critical for rebalance stability. Logging errors like partition leader issues or connection failures should be actively investigated as they may cause unnecessary rebalancing.

#### **Broker Performance Monitoring**
- **Key Metrics**:
    - **`NetworkThreadTime`**: If this is unusually high, it indicates that network processing is delayed, which might cause longer-than-expected response times to consumer heartbeat requests.
    - **`BrokerState`**: Monitor the state of the brokers to ensure they are not entering states such as `LEADER_ELECTION_IN_PROGRESS`, which could delay partition reassignments.

  **Why it's important**: Monitoring network thread times and broker states helps ensure that brokers aren’t blocking due to resource exhaustion or coordination issues, which can directly lead to consumer rebalancing problems.

### **4. Kafka Partitioning Strategy**

#### **Evaluate Topic Partition Strategy**
- **Check the number of partitions**: Too few partitions may overload individual consumers, while too many can lead to **partition management overhead**. Ideally, the number of partitions should match the **expected throughput** and the **number of consumer threads**.

  **Why it's important**: Kafka is designed for parallel consumption, but an imbalance in partitioning can cause **bottlenecks** on specific consumers. Regularly review partitioning strategies to align with consumer capabilities.

#### **Custom Partition Assignment Strategies**
- **Consider Custom Partition Assigners**: If you have specific needs, you might need to implement a **custom partition assigner** (e.g., based on data locality or custom business rules). Kafka allows consumers to use custom partition assignment logic, which can prevent skewed load.

  **Why it's important**: Kafka’s default partition assigners might not work for all business cases. For instance, if consumers are processing different categories of data, you may want to **ensure partitions are evenly distributed** according to those categories, rather than relying solely on the default round-robin approach.

### **5. Network and System Monitoring**

#### **Network Performance and Latency Checks**
- **Tools**:
    - **Ping and traceroute** to test network latency between Kafka brokers and consumer nodes.
    - **TCP/UDP packet analysis** to check for delays or dropped packets, especially during rebalance events.

  **Why it's important**: If there are network delays or packet losses, the **consumer’s ability to send heartbeats** or fetch partitions might be delayed. This could trigger unnecessary rebalances and cause lag issues. Network issues often cause **repeated consumer disconnections** leading to high-frequency rebalancing.

#### **System Resource Utilization**
- **Tools to monitor**:
    - `top`, `htop`, or `sar` on Linux to track CPU, memory, and I/O usage on consumer nodes.
    - **Disk I/O**: If disk operations are slow, it could affect the **consumer’s ability to fetch messages** promptly.

  **Why it's important**: Even if the Kafka configuration is perfect, **system resource limitations** (e.g., CPU or memory throttling) can cause consumers to fall behind. This might result in **heartbeat timeouts** and unnecessary rebalances. Always keep an eye on system resource utilization during heavy traffic times to ensure that the consumer can handle the load.

---

### **6. Advanced Kafka Metrics & Monitoring**

#### **Consumer Lag Metrics with Prometheus**
- **Integrating Prometheus with Kafka** to get detailed metrics on consumer lag, rebalance times, and consumer performance.
    ```yaml
    - job_name: 'kafka-consumer'
      static_configs:
        - targets: ['<kafka-broker>:9092']
    ```

  **Why it's important**: Prometheus integration helps you track the **health of each consumer** over time. By visualizing metrics such as **rebalance time**, **consumer lag**, and **partition availability**, you can proactively identify issues before they impact performance.

#### **Kafka Consumer Lag Alerts**
- **Setting up alerting** based on lag thresholds:
    - If a consumer’s lag exceeds a certain threshold (e.g., 10,000 messages behind), trigger an alert.

  **Why it's important**: Alerts help quickly detect situations where consumers are **falling too far behind** in processing, providing early warning to your team. Having automated alerts reduces the need for manual monitoring and ensures quicker issue identification and resolution.

---

### **Conclusion**

Through these **further diagnostic steps**, you can ensure your Kafka consumer group performs optimally and remains stable under load. Here’s a quick recap of the key areas to continuously monitor:

1. **Consumer group lag and partition assignment** – Regularly check consumer lag and partition distribution.
2. **Broker and network health** – Ensure brokers are healthy and capable of handling requests without significant delays.
3. **Consumer configuration tuning** – Adjust key configuration settings like `max.poll.records`, `session.timeout.ms`, and `heartbeat.interval.ms`.
4. **Partitioning strategy and balancing** – Review and balance partition assignments periodically.
5. **Resource utilization** – Monitor system resources and network latency to avoid bottlenecks.

By continually tuning these aspects and monitoring the system closely, you’ll have a better chance of avoiding consumer rebalancing storms and ensuring Kafka’s smooth operation.


---
Here’s another **Kafka consumer rebalancing** production issue in a detailed conversational style.

---

# **Production Issue: Kafka Consumer Rebalancing Storms**

## **Technical Narrative (War Story)**

### **Scene: The Mystery of Disappearing Consumers**

It started with an innocent question from our on-call engineer:

*"Why are we seeing sudden lag spikes every 10 minutes?"*

Looking at **Kafka consumer metrics**, something strange was happening—every few minutes, all consumers in the Buyer application **dropped out and rejoined** the group. This triggered **a full consumer rebalance**, stalling message processing for a noticeable duration.

What made it even worse? **Lag would spike, then drop to zero, then spike again!**

At first glance, everything *seemed* fine—no crashes, no errors. But something was *causing* Kafka to think our consumers were unhealthy, forcing constant rebalancing.

---

## **Diagnostic Steps & Justifications**

### **Step 1: Check Consumer Group Stability**
**Command:**
```sh
kafka-consumer-groups.sh --describe --group buyer-group
```
**Findings:**
- The **consumer count fluctuated** frequently—some instances disappeared and reappeared.
- Partitions were **rapidly reassigning** among consumers.

**Why this step?**
Kafka rebalances when:
1. **A new consumer joins the group.**
2. **An existing consumer leaves or fails.**
3. **A partition gets reassigned.**

If we see **frequent member churn**, something is making Kafka believe our consumers **aren't stable**.

---

### **Step 2: Check Consumer Logs for Session Timeouts**
**Logs Analysis:**
We checked the logs of multiple Buyer instances and found:
- **Consumers getting removed from the group** due to `"Session expired"` errors.
- `"Member removed from group"` messages appeared **every 10 minutes** like clockwork.

**Why this step?**
Kafka removes consumers when **it doesn’t receive heartbeats in time**. This can happen due to:
1. **Slow processing causing heartbeat delays.**
2. **Poor network connectivity.**
3. **Misconfigured session timeouts.**

Now we needed to figure out **why heartbeats were failing**.

---

### **Step 3: Check Heartbeat Timing**
**Config Check:**
```properties
session.timeout.ms=10000
heartbeat.interval.ms=3000
```
**Findings:**
- The `session.timeout.ms` was **too aggressive** for our processing speed.
- The `heartbeat.interval.ms` was **too close to the session timeout**, leaving no buffer.

**Why this step?**
Kafka expects **consistent heartbeats** from a consumer. If a consumer is slow (e.g., due to processing delays), it might not **send heartbeats in time**, making Kafka think it’s dead.

With a **10s timeout**, **even minor GC pauses or processing delays** could cause the session to expire, forcing a **group rebalance**.

---

### **Step 4: Check Consumer Processing Speed**
**Command:**
```sh
jstack -l <PID>
```
**Findings:**
- Consumer threads were **frequently blocked on database writes**.
- Some consumers were **stuck waiting for a connection from HikariCP**.

**Why this step?**
If a consumer thread **hangs on a slow DB operation**, it might not call `poll()` fast enough. This prevents heartbeats from reaching Kafka **in time**, causing forced removal.

Since we already suspected **heartbeat delays**, this confirmed that our consumer was **too slow to meet Kafka’s expectations**.

---

### **Step 5: Verify Database Performance Bottlenecks**
**SQL Analysis:**
```sql
SELECT * FROM sys.dm_exec_requests WHERE blocking_session_id <> 0;
SELECT * FROM sys.dm_tran_locks WHERE request_status = 'WAIT';
```
**Findings:**
- Some transactions were **holding table locks for too long**.
- Query execution times **spiked around the time consumers dropped out**.
- Connection pooling logs showed **threads waiting for database connections**.

**Why this step?**
If consumers are **blocked waiting for DB connections**, they **can’t poll Kafka** in time. Since polling also sends heartbeats, this leads to **Kafka thinking the consumer is dead**.

---

## **Solution & Fixes**

### **1. Increase Session Timeout to Avoid Premature Consumer Removal**
```properties
session.timeout.ms=45000
heartbeat.interval.ms=15000
```
**Justification:**
- A higher timeout ensures **Kafka won’t kick out a slow consumer too quickly**.
- Increased heartbeat interval **reduces network overhead**, while still maintaining stability.

---

### **2. Optimize Consumer Polling Behavior**
```java
while (true) {
    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
    processRecords(records);
    consumer.commitSync();
}
```
**Justification:**
- Ensures polling happens **before processing**, preventing consumer starvation.
- **More frequent polling → more frequent heartbeats → Kafka keeps the consumer alive.**

---

### **3. Improve Database Performance to Prevent Processing Delays**
- **Added proper indexing** to reduce query execution times.
- **Optimized batch writes** instead of individual transactions.
- Increased **HikariCP max connections** to prevent connection starvation:
  ```properties
  maximumPoolSize=20
  ```

**Justification:**
- Faster DB writes **prevent consumer threads from getting stuck**.
- More connections **reduce waiting time** for a DB slot.

---

### **4. Use Static Group Membership to Prevent Unnecessary Rebalancing**
```properties
group.instance.id=buyer-consumer-1
```
**Justification:**
- **Prevents full rebalances when a consumer temporarily disconnects** (e.g., during restarts).
- Consumers keep their partitions, reducing lag spikes.

---

## **Lessons Learned**

✅ **Don’t Let Consumers Starve**: If `poll()` is delayed, Kafka thinks the consumer is dead. **Ensure frequent polling!**

✅ **Session Timeouts Matter**: Setting it **too low** can cause unnecessary rebalancing.

✅ **Database Delays Kill Kafka Performance**: If your consumer **depends on slow DB writes**, you’ll end up with **rebalancing storms**.

✅ **Use Static Membership When Possible**: If consumers are stable, **static group IDs prevent excessive rebalancing.**

---

### **Final Outcome**

Once these fixes were deployed:
✅ Kafka **stopped triggering unnecessary rebalances**.
✅ Consumers **maintained stable partitions** without frequent drops.
✅ **Message lag decreased** significantly.

This was another case of **misconfigured consumer settings interacting badly with database delays**, but once we identified the root cause, everything **snapped back to normal**.

---

### **Production Issue Investigation: Kafka Consumer Starvation Due to Slow Processing in a Struts 2.x System**

#### **Technical Narrative (War Story)**

##### **Scene: The Issue Emerges**
An urgent escalation came in: **Kafka messages weren’t being processed fast enough**, and consumer lag was growing rapidly. The Buyer team noticed that **new messages were piling up**, but the consumer logs showed no errors—just **sporadic processing bursts** with long idle gaps.

Meanwhile, the **Supplier instance was functioning normally**, continuously producing messages at the expected rate.

##### **Legacy Quirks and Pitfalls**
- **Struts 2.x Blocking Behavior**: The consumer thread was unintentionally blocked inside a Struts action due to **synchronous database writes** and **unoptimized batch processing**.
- **Excessive Polling Delays**: The consumer’s `poll()` method wasn't being called frequently enough, causing Kafka to assume it was inactive.
- **Database Bottlenecks**: Buyer DB queries were running slower than usual, worsening the delay.

---

## **Diagnostic Steps & Justifications**

### **Step 1: Check Consumer Lag and Processing Rate**
**Command:**
```sh
kafka-consumer-groups.sh --describe --group buyer-group
```
**What we found:**
- The lag on some partitions had climbed **above 100,000 messages**.
- The **consumer’s offset wasn’t progressing consistently**—suggesting processing delays rather than a complete failure.

**Why we did this step:**  
Consumer lag is the **first metric to check** because it tells us whether the consumer is keeping up with the producer. A growing lag with **inconsistent offset movement** often means that processing is happening, but not at a steady pace.

---

### **Step 2: Analyze Consumer Logs for Polling Frequency Issues**
**What we found:**
- Logs showed **long gaps (10+ seconds) between successive polls**.
- Frequent `Rebalance in progress` messages, indicating that Kafka was **removing and re-adding the consumer to the group**.

**Why we did this step:**  
In Kafka, if a consumer doesn’t call `poll()` **within `max.poll.interval.ms`**, the broker assumes it’s dead and kicks it out of the group. This triggers a rebalance, causing message processing to pause momentarily.

Since the logs showed **sporadic bursts of message consumption**, we suspected that the consumer was stuck **between poll calls**—most likely due to slow processing.

---

### **Step 3: Thread Dump Analysis**
**Command:**
```sh
jstack -l <PID>  
```
**What we found:**
- The Kafka consumer thread was frequently **blocked on a database write operation** inside the Struts action.
- `java.sql.PreparedStatement.executeUpdate()` calls were holding the thread for **several seconds**.
- Some transactions were waiting on **locks in the Buyer database**.

**Why we did this step:**  
A consumer **must call `poll()` frequently**, or it gets removed from the group. If the **consumer thread is blocked on external operations** (like a database write), it cannot poll in time, leading to:
1. **Starvation:** Messages aren’t fetched fast enough.
2. **Rebalancing:** Kafka kicks out the consumer, disrupting processing.

By checking a thread dump, we confirmed that the issue wasn’t a consumer crash—it was **being blocked by slow database writes**.

---

### **Step 4: Database Performance Analysis**
**SQL Queries Used:**
```sql
SELECT * FROM sys.dm_exec_requests WHERE blocking_session_id <> 0;
SELECT * FROM sys.dm_tran_locks WHERE request_status = 'WAIT';
```
**What we found:**
- Several database transactions were **waiting on row-level locks**.
- Queries had **high execution times**, especially updates to a **Buyer transactions table**.
- Missing indexes on **foreign key joins** were forcing full table scans.

**Why we did this step:**  
Since we already suspected **database writes were blocking Kafka polling**, we needed to confirm:
1. **Were there slow queries?** → Yes, transaction times were **much higher than expected**.
2. **Were multiple consumers fighting over the same resource?** → Yes, lock contention was causing delays.
3. **Was indexing a problem?** → Yes, lack of indexes was slowing down updates.

This step confirmed that **database performance issues were the root cause of the consumer starvation**.

---

## **Solution & Fixes**

### **1. Optimize Kafka Consumer Polling Behavior**
- Reduced `max.poll.records` to allow smaller, faster batches:
  ```properties
  max.poll.records=20
  ```
- Increased `max.poll.interval.ms` to **prevent premature rebalances**:
  ```properties
  max.poll.interval.ms=600000
  ```
- Moved the database write operation **outside the critical polling loop**:
  ```java
  while (true) {
      ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
      List<Data> batch = new ArrayList<>();
      
      for (ConsumerRecord<String, String> record : records) {
          batch.add(parseData(record));
      }
      
      if (!batch.isEmpty()) {
          processBatchInDB(batch);  // Offloaded DB writes
      }
      
      consumer.commitSync();
  }
  ```

**Justification:**
- **Smaller batch sizes prevent DB overload.**
- **Longer poll intervals reduce unnecessary rebalancing.**
- **Offloading DB writes prevents blocking Kafka polling.**

---

### **2. Fix Database Bottlenecks**
- Added missing indexes on **foreign key joins** to speed up queries.
- Optimized bulk inserts using **JDBC batching**:
  ```java
  PreparedStatement stmt = conn.prepareStatement(sql);
  for (Data data : batch) {
      stmt.setString(1, data.getValue());
      stmt.addBatch();
  }
  stmt.executeBatch();
  ```

**Justification:**
- **Indexes reduced query execution time by 60%.**
- **Batch inserts significantly lowered transaction locking time.**

---

### **3. Reduce Consumer Processing Time**
- Moved expensive computations **outside the Kafka consumer thread** using an executor pool:
  ```java
  ExecutorService executor = Executors.newFixedThreadPool(10);
  
  while (true) {
      ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
      
      for (ConsumerRecord<String, String> record : records) {
          executor.submit(() -> processRecord(record));  // Async processing
      }
      
      consumer.commitSync();
  }
  ```

**Justification:**
- **Parallel execution prevents consumer thread from blocking.**
- **Allows Kafka polling to run continuously without waiting for slow operations.**

---

## **Lessons Learned**

### **Kafka-Specific Takeaways**
✅ **Never block Kafka consumers with slow operations.** Keep them lightweight and use async processing.  
✅ **Monitor consumer lag proactively.** Growing lag without errors is a sign of **processing bottlenecks**.  
✅ **Poll frequently.** If `poll()` isn’t called often enough, Kafka assumes the consumer is dead.

### **Struts 2.x Legacy Considerations**
✅ **Struts actions are not designed for long-running tasks.** Move heavy processing outside.  
✅ **Be mindful of JDBC connection handling.** Unoptimized writes can **cripple** consumers.  
✅ **Threading must be explicitly managed.** Struts doesn’t automatically handle concurrent workloads well.

---

### **Final Outcome**
After implementing these changes, Kafka consumer lag **dropped to near zero**, and messages were processed in real-time. The system is now **more resilient**, and Buyer DB writes are no longer a bottleneck.

---

### **Production Issue Investigation: Kafka Consumer Lags in a Legacy Struts 2.x System**

#### **Technical Narrative (War Story)**

##### **Scene: The Issue Emerges**

It started with an escalation from a Buyer instance team: database syncs were lagging, leading to outdated records. Meanwhile, Kafka consumer lag metrics were steadily climbing, with some partitions showing lags in the tens of thousands. The Supplier instance, however, continued to send messages at its usual rate.

##### **Legacy Quirks and Pitfalls**

- **Struts 2.x Thread Model Issues**: The consumer logic was embedded inside a Struts `Action` class, which was not designed for multi-threaded execution. This led to unintended state retention across requests.
- **JDBC Connection Leaks**: HikariCP logs revealed `leakDetectionThreshold` violations, indicating connections weren’t being closed properly.
- **Kafka Consumer Misconfiguration**: Consumers were using default `max.poll.records=500`, leading to batch processing that overwhelmed the DB writes and caused frequent rebalances.

#### **Diagnostic Steps**

##### **1. Kafka Diagnostics**

- Ran `kafka-consumer-groups.sh --describe --group buyer-group` and found partition imbalance: some consumers had much higher lags than others.
- Checked broker-side metrics (`kafka.server:type=KafkaRequestHandlerPool,name=RequestHandlerAvgIdlePercent`) and saw thread starvation.
- Observed spikes in `NetworkThreadBlockTime`, indicating slow fetches from the consumer side.

##### **2. JVM/Thread Analysis**

- Captured `jstack` dumps and found that Struts `ActionContext` was holding onto a HikariCP connection longer than necessary, likely due to improper connection handling.
- Checked Tomcat thread pools (`Catalina-exec-*`) and found multiple blocked threads waiting for database connections.
- Reviewed Struts configurations and discovered actions defined as singletons (`struts.objectFactory.singleton=true`), causing concurrency issues.

##### **3. Database Diagnostics**

- Ran SQL Server DMVs:
  - `sys.dm_exec_requests` showed high lock waits on Buyer DBs.
  - `sys.dm_os_wait_stats` had spikes in `WRITELOG`, indicating high transaction log pressure.
  - Unindexed queries in SQL statements sent via Kafka were causing full table scans.

#### **Solution**

##### **1. Fixing Struts 2.x Threading Issues**

- Updated actions to prototype scope:
  ```xml
  <constant name="struts.objectFactory.singleton" value="false"/>
  ```
- Ensured database connections were closed properly within actions:
  ```java
  try (Connection conn = dataSource.getConnection()) {
      // Execute SQL
  } catch (SQLException e) {
      log.error("DB error", e);
  }
  ```

##### **2. Kafka Consumer Tuning**

- Reduced batch size to prevent DB overload:
  ```properties
  max.poll.records=50
  max.poll.interval.ms=300000
  ```
- Increased session timeout to handle processing delays more gracefully:
  ```properties
  session.timeout.ms=45000
  heartbeat.interval.ms=15000
  ```

##### **3. Database Optimizations**

- Added missing indexes on Buyer DB tables.
- Implemented batching in SQL execution to reduce commit overhead:
  ```java
  PreparedStatement stmt = conn.prepareStatement(sql);
  for (Data data : batch) {
      stmt.setString(1, data.getValue());
      stmt.addBatch();
  }
  stmt.executeBatch();
  ```

##### **4. Workarounds for Immediate Relief**

- Performed **dynamic partition reassignment** to redistribute load across consumers.
- Increased HikariCP `maxLifetime` to ensure stale connections were pruned:
  ```properties
  maxLifetime=1800000
  connectionTimeout=30000
  ```

#### **Lessons Learned**

##### **Architectural Takeaways**

- **Decoupling with SQL statements in Kafka is risky.** Ingesting raw SQL creates idempotency and execution order issues. Moving towards event-based messaging would help.
- **Struts 2.x singleton behavior is a silent killer.** Ensure action beans are stateless and properly scoped.
- **Thread safety needs explicit handling in legacy frameworks.** Connection handling should always be explicit in a multi-threaded environment.

##### **Kafka-Specific Learnings**

- **Monitor consumer lag proactively.** Use Prometheus/Grafana alerts for lag spikes.
- **Dynamic partition balancing helps mitigate overloads.** Manually reassigning partitions can buy time until a more permanent fix is applied.
- **Batch tuning is critical.** Fine-tuning `max.poll.records` and `max.poll.interval.ms` can prevent system overloads.

---

### **Production Issue Investigation: Kafka Consumer Rebalancing Loop in a Struts 2.x System**

#### **Technical Narrative (War Story)**

##### **Scene: The Issue Emerges**

A Buyer instance reported that its Kafka consumer was stuck in a continuous rebalancing loop, preventing it from processing messages. The application logs were flooded with `Consumer group rebalancing` messages, and consumer lag remained static. Meanwhile, the Supplier instance continued producing messages as expected.

##### **Legacy Quirks and Pitfalls**

- **Session Timeout vs. Processing Time Mismatch**: The consumer’s processing time exceeded the configured `session.timeout.ms`, leading Kafka to assume the consumer was dead and triggering rebalances.
- **Struts 2.x Dependency Injection Issues**: Some consumers were unexpectedly recreated due to the framework’s object lifecycle handling, causing session inconsistencies.
- **Improper Commit Handling**: The consumer was using `enable.auto.commit=true`, leading to potential duplicate processing when rebalances occurred.

#### **Diagnostic Steps**

##### **1. Kafka Diagnostics**

- Checked consumer group status with `kafka-consumer-groups.sh --describe` and observed frequent member changes.
- Monitored logs and found `org.apache.kafka.clients.consumer.CommitFailedException`, indicating offsets were lost due to consumer rebalances.
- Verified broker logs and saw `Member id changed` messages for the Buyer consumer group.

##### **2. JVM/Thread Analysis**

- Captured `jstack` dumps and found that long-running database queries were holding up consumer threads.
- Checked thread activity and found multiple instances of the consumer running simultaneously due to Struts 2.x’s object lifecycle mismanagement.

##### **3. Database Diagnostics**

- Ran SQL Server DMVs and found long-running transactions causing contention on Buyer DB tables.
- Identified lack of indexing on key lookup fields used in SQL queries.

#### **Solution**

##### **1. Kafka Consumer Tuning**

- Increased session timeout to accommodate longer processing times:
  ```properties
  session.timeout.ms=60000
  heartbeat.interval.ms=20000
  ```
- Switched to manual offset commits to prevent duplicate processing:
  ```java
  consumer.commitSync();
  ```

##### **2. Fixing Struts Object Lifecycle Issues**

- Ensured Kafka consumers were managed as singletons within Struts:
  ```xml
  <constant name="struts.objectFactory.singleton" value="true"/>
  ```
- Wrapped consumer logic in a managed executor to prevent multiple instantiations.

##### **3. Database Optimizations**

- Tuned slow queries and added necessary indexes to reduce query times.
- Implemented connection pooling best practices to prevent long-held connections.

#### **Lessons Learned**

- **Monitor rebalancing frequency.** Excessive rebalances indicate deeper architectural issues.
- **Ensure consumer session timeouts align with processing times.** Mismatches can lead to unnecessary rebalancing.
- **Manage Kafka consumers explicitly.** Struts' object lifecycle quirks can cause unwanted behavior if not properly handled.

By implementing these changes, the rebalancing loops were eliminated, and consumers resumed normal operation.

---

Here’s a **detailed, flow-driven war story** with technical depth, diagnostic rigor, and lessons learned—structured like a postmortem for a Kafka consumer lag issue in your legacy Struts 2.x system:

---

### **War Story: The Kafka Consumer Lag That Broke the Buyer-Supplier Sync**

#### **1. Symptoms: The Silent Data Divergence**
- **Observed**: Buyers reported stale data; dashboards showed mismatches with Supplier records.
- **Kafka Metrics**: Lag spikes (100K+ messages) *only* for certain Buyer instances.
- **Odd Clue**: Supplier-side DB load was normal, but Buyer-side DBs showed elevated `WRITELOG` waits.

#### **2. Initial Hypotheses**
- **Kafka Broker Issues?** Unlikely—broker metrics (`NetworkThreadBlockTime`, `RequestQueueSize`) were green.
- **Network Latency?** `tcpdump` showed no packet drops between Supplier and Buyers.
- **Consumer Starvation?** Focused here next.

---

### **3. Deep Dive: The Diagnostic Journey**

#### **Step 1: Kafka Consumer Forensics**
- **Tool**: `kafka-consumer-groups.sh --describe --bootstrap-server <broker>`
  - **Finding**: 3/10 Buyer instances had 90% of lag, all sharing the same partition.
  - **Smell**: Skewed partition assignment (default `RangeAssignor` + static topic naming).

#### **Step 2: Thread and JVM Inspection**
- **Tool**: `jstack <consumer_pid>` + HikariCP logs.
  - **Finding**:
    - Struts `ActionProxy` instances (singletons!) held JDBC connections for 30+ seconds.
    - Hikari leak logs: `Connection leak detection triggered for thread "kafka-consumer-thread-17"`.
  - **Root Cause**:
    - Struts 2.x actions (default scope=singleton) reused across HTTP requests *and* Kafka consumer threads.
    - A slow `BuyerDataSyncAction` blocked the entire consumer thread pool.

#### **Step 3: Database Contention**
- **Tool**: SQL Server DMVs:
  ```sql
  SELECT * FROM sys.dm_exec_requests WHERE blocking_session_id <> 0;  
  SELECT wait_type, wait_time_ms FROM sys.dm_os_wait_stats ORDER BY wait_time_ms DESC;  
  ```
  - **Finding**:
    - `WRITELOG` waits (Buyer DBs) correlated with Kafka consumer commits.
    - One Buyer’s DB had a rogue `DELETE FROM audit_log` with no index → table lock.

---

### **4. The Fix: Thread Safety, Tuning, and Idempotency**

#### **Fix 1: Struts 2.x Thread Safety**
- **Problem**: Singleton actions sharing state across threads.
- **Solution**: Force prototype scope and isolate Kafka/HTTP traffic:
  ```xml
  <struts>  
    <constant name="struts.objectFactory.spring.autoWire" value="type"/>  
    <package name="kafka-actions" extends="struts-default" scope="prototype">  
      <!-- Actions used by Kafka consumers -->  
    </package>  
  </struts>  
  ```

#### **Fix 2: Kafka Consumer Tuning**
- **Problem**: Consumers stuck in `poll()` due to slow processing.
- **Solution**: Adjust timeouts and batch size:
  ```properties
  max.poll.records=50 # Down from 500  
  session.timeout.ms=30000 # Up from 10s  
  max.poll.interval.ms=120000 # Allow slower processing  
  isolation.level=read_committed # Prevent dirty reads from Supplier  
  ```

#### **Fix 3: HikariCP + SQL Server Optimizations**
- **Problem**: Leaked connections during long-running syncs.
- **Solution**:
  ```properties
  leakDetectionThreshold=60000 # Log leaks early  
  connectionTimeout=250ms # Fail fast during contention  
  ```
- **DB Fix**: Added index on `audit_log(timestamp)` + batched deletes.

---

### **5. Lessons Learned (The Hard Way)**
1. **Struts 2.x in Event-Driven Systems**:
   - **Anti-Pattern**: Mixing HTTP and Kafka workloads in singleton actions.
   - **Takeaway**: Isolate Kafka consumers in plain Java threads or use `@Scope("prototype")`.

2. **Kafka Consumer Pitfalls**:
   - **Skew Killer**: Use `StickyAssignor` for even partition distribution.
   - **Idempotency Gap**: SQL statements in messages risk duplicates—add `WHERE NOT EXISTS` clauses.

3. **Observability Gaps**:
   - **Missed Signal**: No alerts on consumer thread pool saturation. Added `kafka.consumer:type=consumer-fetch-manager-metrics` monitoring.

#### **Architectural Wake-Up Call**
- **The Real Issue**: Tight coupling via SQL messages. Future state: Switch to CDC (Debezium) + Avro.
- **Creative Hack**: Used Kafka transactions (`isolation.level=read_committed`) as a bandaid.

---

### **Tone & Key Takeaways**
- **Principal Engineer Vibe**:
  > *"Lesson: Struts 2.x singletons and Kafka consumers mix like oil and water. We jury-rigged it with prototype scope, but the real fix is isolating I/O-bound workloads."*
- **Legacy Quirk Highlight**:
  > *"Struts `ActionContext` inherits Tomcat’s thread-local hell—watch for leaked request attributes in consumer threads!"*

This narrative **flows logically from symptoms to root cause**, with each step justified by data (not guesses). Let me know if you'd like to emphasize other angles!

---