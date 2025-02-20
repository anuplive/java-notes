Here are the key concepts in **Java Concurrency**:

### 1. **Threads & Thread Lifecycle**
   - `Thread` class & `Runnable` interface
   - Thread states: New, Runnable, Blocked, Waiting, Timed Waiting, Terminated
   - Daemon vs. User threads

### 2. **Thread Synchronization**
   - **`synchronized` keyword** (method-level, block-level)
   - **Intrinsic Locks (Monitor locks)**
   - **Reentrant Locks (`ReentrantLock`)**

### 3. **Inter-Thread Communication**
   - **`wait()`, `notify()`, `notifyAll()`** (Object class methods)
   - **`join()`** (Waiting for a thread to finish)
   - **`yield()` & `sleep()`** (Pausing thread execution)

### 4. **Java Memory Model (JMM)**
   - Visibility & Atomicity issues
   - Happens-before relationships
   - Volatile keyword (`volatile` guarantees visibility but not atomicity)

### 5. **Concurrency Utilities (`java.util.concurrent`)**
   - **Executor Framework (`Executor`, `ExecutorService`, `ThreadPoolExecutor`)**
   - **Callable & Future** (Returning results from threads)
   - **Fork/Join Framework** (Divide-and-conquer parallelism)
   - **CompletableFuture** (Asynchronous programming)

### 6. **Locks & Synchronizers**
   - **Explicit Locks (`ReentrantLock`, `ReadWriteLock`)**
   - **Condition variables (`Lock.newCondition()`)**
   - **Atomic variables (`AtomicInteger`, `AtomicReference`, etc.)**

### 7. **Thread-safe Collections (`java.util.concurrent`)**
   - **ConcurrentHashMap**
   - **CopyOnWriteArrayList**
   - **BlockingQueue (`ArrayBlockingQueue`, `LinkedBlockingQueue`)**
   - **ConcurrentSkipListMap, ConcurrentLinkedQueue**

### 8. **Latches, Barriers & Semaphores**
   - **CountDownLatch** (Waiting for multiple threads)
   - **CyclicBarrier** (Synchronizing multiple threads)
   - **Semaphore** (Controlling thread access)

### 9. **Thread Local Storage**
   - **`ThreadLocal<T>`** (Per-thread storage)

### 10. **Deadlocks, Starvation & Livelocks**
   - Deadlock (Circular waiting on locks)
   - Starvation (Low-priority thread never executes)
   - Livelock (Threads actively changing states but making no progress)

Would you like me to dive deeper into any specific concept? 🚀


