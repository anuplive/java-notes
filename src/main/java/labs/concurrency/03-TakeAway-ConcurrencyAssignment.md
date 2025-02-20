Below is a **comprehensive cheat sheet** covering all the important **Java Concurrency and Exception Handling** concepts related to **Order Processing** that we have covered so far. This cheat sheet is designed to be a quick reference guide for students.

---

## **Java Concurrency and Exception Handling Cheat Sheet**

---

### **1. Basic Order Class**
- **Purpose:** Represents an order with `orderId`, `customerName`, and `amount`.
- **Key Code:**
  ```java
  public class Order {
      private final int orderId;
      private final String customerName;
      private final double amount;

      public Order(int orderId, String customerName, double amount) {
          this.orderId = orderId;
          this.customerName = customerName;
          this.amount = amount;
      }

      // Getters and toString
  }
  ```

---

### **2. Single-Threaded Order Processor**
- **Purpose:** Processes orders sequentially.
- **Key Code:**
  ```java
  public class OrderProcessor {
      public void processOrder(Order order) {
          System.out.println("Processing order: " + order);
          try {
              Thread.sleep(1000); // Simulate processing
          } catch (InterruptedException e) {
              System.err.println("Interrupted: " + e.getMessage());
          }
          System.out.println("Order processed: " + order);
      }
  }
  ```

---

### **3. Multi-Threaded Order Processing with `Runnable`**
- **Purpose:** Processes orders concurrently using `Runnable`.
- **Key Code:**
  ```java
  public class OrderProcessor implements Runnable {
      private final Order order;

      public OrderProcessor(Order order) {
          this.order = order;
      }

      @Override
      public void run() {
          System.out.println("Processing order: " + order);
          try {
              Thread.sleep(1000);
          } catch (InterruptedException e) {
              System.err.println("Interrupted: " + e.getMessage());
          }
          System.out.println("Order processed: " + order);
      }
  }
  ```

---

### **4. Executor Framework**
#### **FixedThreadPool**
- **Purpose:** Manages a fixed number of threads.
- **Key Code:**
  ```java
  ExecutorService executor = Executors.newFixedThreadPool(4);
  executor.submit(new OrderProcessor(order));
  executor.shutdown();
  ```

#### **CachedThreadPool**
- **Purpose:** Dynamically manages threads.
- **Key Code:**
  ```java
  ExecutorService executor = Executors.newCachedThreadPool();
  executor.submit(new OrderProcessor(order));
  executor.shutdown();
  ```

#### **ScheduledThreadPool**
- **Purpose:** Schedules tasks with a delay.
- **Key Code:**
  ```java
  ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
  executor.schedule(new OrderProcessor(order), 5, TimeUnit.SECONDS);
  executor.shutdown();
  ```

#### **WorkStealingPool**
- **Purpose:** Parallel processing using work-stealing algorithm.
- **Key Code:**
  ```java
  ExecutorService executor = Executors.newWorkStealingPool();
  executor.submit(new OrderProcessor(order));
  executor.shutdown();
  ```

---

### **5. Futures and CompletableFuture**
#### **Future**
- **Purpose:** Represents the result of an asynchronous computation.
- **Key Code:**
  ```java
  Future<String> future = executor.submit(() -> {
      return "Order processed: " + order;
  });
  System.out.println(future.get());
  ```

#### **CompletableFuture**
- **Purpose:** Asynchronous programming with chaining and exception handling.
- **Key Code:**
  ```java
  CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
      return "Order processed: " + order;
  });
  future.thenAccept(System.out::println);
  ```

---

### **6. Error Handling in Concurrent Applications**
#### **Handling `InterruptedException`**
- **Purpose:** Handle thread interruption.
- **Key Code:**
  ```java
  try {
      Thread.sleep(1000);
  } catch (InterruptedException e) {
      System.err.println("Interrupted: " + e.getMessage());
  }
  ```

#### **Handling `ExecutionException`**
- **Purpose:** Handle exceptions in `Future`.
- **Key Code:**
  ```java
  try {
      future.get();
  } catch (ExecutionException e) {
      System.err.println("Execution failed: " + e.getMessage());
  }
  ```

#### **Shutdown Executors**
- **Purpose:** Gracefully shutdown executors.
- **Key Code:**
  ```java
  executor.shutdown();
  executor.awaitTermination(1, TimeUnit.MINUTES);
  ```

---

### **7. ThreadLocal and InheritableThreadLocal**
#### **ThreadLocal**
- **Purpose:** Store thread-specific data.
- **Key Code:**
  ```java
  private static final ThreadLocal<Order> currentOrder = new ThreadLocal<>();
  currentOrder.set(order);
  System.out.println("Processing: " + currentOrder.get());
  currentOrder.remove();
  ```

#### **InheritableThreadLocal**
- **Purpose:** Pass thread-specific data to child threads.
- **Key Code:**
  ```java
  private static final InheritableThreadLocal<Order> currentOrder = new InheritableThreadLocal<>();
  ```

---

### **8. Latches, Barriers, and Semaphores**
#### **CountDownLatch**
- **Purpose:** Wait for multiple threads to complete.
- **Key Code:**
  ```java
  CountDownLatch latch = new CountDownLatch(3);
  executor.submit(() -> {
      latch.countDown();
  });
  latch.await();
  ```

#### **CyclicBarrier**
- **Purpose:** Synchronize multiple threads at a barrier.
- **Key Code:**
  ```java
  CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println("All orders processed"));
  executor.submit(() -> {
      barrier.await();
  });
  ```

#### **Semaphore**
- **Purpose:** Control access to a shared resource.
- **Key Code:
  ```java
  Semaphore semaphore = new Semaphore(2);
  executor.submit(() -> {
      semaphore.acquire();
      // Process order
      semaphore.release();
  });
  ```

---

### **9. Advanced Exception Handling with CompletableFuture**
- **Purpose:** Handle exceptions in asynchronous tasks.
- **Key Code:
  ```java
  CompletableFuture.supplyAsync(() -> {
      if (order.getAmount() < 0) {
          throw new IllegalArgumentException("Invalid amount");
      }
      return "Order processed: " + order;
  }).exceptionally(ex -> {
      System.err.println("Failed: " + ex.getMessage());
      return "Order processing failed";
  });
  ```

---

### **10. Combining CompletableFuture Results**
- **Purpose:** Combine results from multiple `CompletableFuture` tasks.
- **Key Code:
  ```java
  CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Order 1");
  CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Order 2");
  CompletableFuture<Void> allFutures = CompletableFuture.allOf(future1, future2);
  allFutures.thenRun(() -> System.out.println("All orders processed"));
  ```

---

### **Key Takeaways**
1. **Concurrency Basics:**
   - Use `Runnable` for tasks without results.
   - Use `Callable` for tasks with results.
   - Use `ExecutorService` to manage threads.

2. **Advanced Concurrency:**
   - Use `CompletableFuture` for asynchronous programming.
   - Use `ThreadLocal` for thread-specific data.
   - Use `CountDownLatch`, `CyclicBarrier`, and `Semaphore` for synchronization.

3. **Exception Handling:**
   - Handle `InterruptedException` and `ExecutionException`.
   - Use `exceptionally` in `CompletableFuture` for error handling.

4. **Best Practices:**
   - Always shut down executors gracefully.
   - Use synchronization tools to avoid race conditions.

---

This cheat sheet provides a quick reference to all the key concepts and code snippets covered in the assignment. It is designed to help students quickly recall and apply these concepts in their projects.