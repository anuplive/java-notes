## Java Multithreading and Concurrency - Basics
[HOME.md](./HOME.md)
=================
## Table of contents

***
### Thread Life Cycle in Java
[Back to Top](#Table-of-contents)
- **New**: Thread is created but not yet started.
- **Runnable**: Thread is ready to run and waiting for CPU time.
- **Running**: Thread is executing.
- **Blocked**: Thread is waiting for a monitor lock to enter/re-enter a synchronized block/method.
- **Waiting**: Thread is waiting indefinitely for another thread to perform a particular action.
- **Timed Waiting**: Thread is waiting for another thread to perform an action for up to a specified waiting time.
- **Terminated**: Thread has exited.

***
### Ways to start Thread in Java !!
[Back to Top](#Table-of-contents)
- **Extending Thread class**: Subclass `Thread` and override the `run()` method.
- **Implementing Runnable interface**: Implement the `Runnable` interface and pass an instance to a `Thread` object.
- **Using Callable and Future**: Implement `Callable` interface and use `FutureTask`.

```java
// Using Callable and Future
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
// Implement a Callable interface and override call()
class MyCallable implements Callable<String> {
    public String call() {
        return "Callable thread is running";
    }
}
// Extending Thread class
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread is running");
    }
}
// Implementing Runnable interface
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Thread is running");
    }
}

public class ThreadCreationDemo {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start(); // Starting the thread
        
        Thread t2 = new Thread(new MyRunnable()); 
        t2.start();
        
        ExecutorService executor  = Executors.newSingleThreadExecutor(); 
        Future<String> futureString =  es.submit(new MyCallable());
        
        try{
            System.out.println(futureString.get());
        }catch(InterruptedException  | ExecutionException ee){
            ee.printStackTrace();
        }finally{
            executor.shutdown();
        }
    }
}
```
***

### Wait for Threads to finish !!
[Back to Top](#table-of-contents)
- **Thread.join()**: The `join()` method allows one thread to wait for the completion of another.
- **Thread.join(timeout)**: Overloaded version with timeout. The waiting thread will wait for the specified time for the other thread to finish.
- **ExecutorService.awaitTermination()**: Waits for all tasks to complete after shutdown.

```java
// Using Thread.join()

import java.util.concurrent.Executors;public class JoinDemo {
    public static void main(String[] args) {
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();
        ExecutorService executor = Executors.newSingleThreadExecutor(); 
        t1.start();
        t2.start();
        executor.execute(() -> {System.out.println("Test Execute");}); 
        try {
            t1.join(); // Main thread waits for t1 to finish
            t2.join(); // Main thread waits for t2 to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        executor.shutdown(); 
        try{
            if (!executor.awaitTermination(20, TimeUnit.SECONDS)){
                executor.shutdownNow();    
            }
        }catch(InterruptedException ie){
            ie.printStackTrace();
            executor.shutdownNow(); 
        }
        System.out.println("All threads have finished executing.");
    }
}

```

***
### Demon Threads and User Threads !!
[Back to Top](#table-of-contents)
- **User Threads**: Normal threads that the JVM waits to complete before exiting.
- **Daemon Threads**: Background threads that support user threads, JVM exits when only daemon threads are left.
- **Setting Daemon**: Use `setDaemon(true)` method.
- **Default Type**: Threads are user threads by default.
- **Key Point**: Daemon threads are terminated when all user threads finish their execution.

```java
// Daemon Thread Example
public class DaemonThreadDemo {
    public static void main(String[] args) {
        DaemonThread t2 = new DaemonThread();
        t2.setDaemon(true); // Setting thread as daemon
        t2.start(); // Starting the daemon thread
        t2.isDeamon(); // Will return true if the thread is a daemon thread. 
        
}}
```


***
### Thread Priority and Thread Scheduler
[Back to Top](#table-of-contents)
- **Thread Priority**:
    - **Higher** priority threads are more likely to be executed, but it depends on the thread scheduler.
    - **Range**: MIN_PRIORITY (1) to MAX_PRIORITY (10).
    - Default priority: NORM_PRIORITY (5).
    - **Usage**: `thread.setPriority(int priority)`.

- **Thread Scheduler**:
    - Part of the JVM responsible for scheduling threads.
    - **Platform-dependent**: Behavior may vary across different operating systems.
    - Uses thread priority as a hint but does not guarantee high priority threads will execute first.
    - **Preemptive scheduling**: Higher priority threads can preempt lower priority ones.
    - **Time-slicing**: Threads are given equal time slots to execute, irrespective of priority.

***
### Memory Management of Threads
[Back to Top](#table-of-contents)
- **Thread Stack**: Each thread has its own stack memory for storing local variables, method call frames, and return addresses.
- **Heap Memory**: Shared among all threads. Objects created by threads are stored in heap memory.
- **Thread Local Storage**: Each thread can have its own isolated storage using `ThreadLocal`.
- **Garbage Collection**: Managed by the JVM; threads can make objects eligible for garbage collection.
- **Synchronization**: Proper synchronization is needed to manage shared resources and avoid memory consistency errors.

####Details on each topic on Sheet3.  
***
### Synchronization in Java
[Back to Top](#Table-of-contents)
- **Thread Interference**: When multiple threads access shared data and the outcome depends on the timing of their execution.
- **Critical Section**: Code that accesses shared resources and needs synchronization to prevent interference.
- **Synchronized Keyword**: Ensures that only one thread at a time executes a synchronized block/method on the same object or class.
- **Intrinsic Lock (Monitor Lock)**: Every object/ class  in Java has an intrinsic lock associated with it, acquired/released by synchronized blocks/methods.
- **Synchronized Block**: Allows more granular control over synchronization than synchronized methods.
- **Reentrant Synchronization**: A thread can acquire the same lock multiple times if it's already holding the lock.
- **Static Synchronization**: Applies to the class's Class object instead of the instance.
- **Volatility**: Ensures visibility of shared variables across threads.
- **Memory Consistency**: Ensures proper visibility of changes to variables shared across threads.

```java
// Example demonstrating synchronization using synchronized keyword and intrinsic lock

class Counter {
    private static int counterstatic = 0; 
    private int count = 0;
    
    // Static synchronized method 
    public static synchronized incrementStatic(){
        counterStatic++; 
    }
    
    // Static synchronized block 
    public static decrementStatic(){
        synchronized(Counter.class){
            counterStatic ++; 
        }
    }
    // Synchronized method
    public synchronized void increment() {
        count++;
    }

    // Synchronized block
    public void decrement() {
        synchronized (this) {
            count--;
        }
    }

    public int getCount() {
        return count;
    }
}

public class SynchronizationDemo {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        // Creating multiple threads to access and modify shared counter
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.decrement();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Final Count: " + counter.getCount()); // Expected output: 0
    }
}
```

***
### Problems with Synchronization in Java
##### TO DO Add Live-Lock, Thread Stravation and RaceCondition Examples
[Back to Top](#Table-of-contents)
- **Deadlock**: Two or more threads are blocked forever, waiting for each other to release locks.
- **Starvation**: A thread is unable to gain regular access to shared resources and is unable to make progress.
- **Livelock**: Threads are not blocked but still unable to make progress due to their actions being constantly undone by each other.
- **Race Condition**: Multiple threads access a shared resource and the outcome depends on the order of execution.
- **Thread Interference**: Two threads are accessing/modifying a shared resource concurrently, resulting in unexpected behavior.

```java
// Example demonstrating a deadlock scenario
// Example demonstrating a deadlock scenario
class SharedResource {
    // Method1 tries to acquire lock on resource1 and then calls method2 on resource2
    synchronized void method1(SharedResource resource) {
        System.out.println(Thread.currentThread().getName() + " is executing method1");
        try {
            Thread.sleep(1000); // Introducing delay to exaggerate the deadlock scenario
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resource.method2(this); // Method1 now calls method2 on resource2
    }

    // Method2 tries to acquire lock on resource2 and then calls method1 on resource1
    synchronized void method2(SharedResource resource) {
        System.out.println(Thread.currentThread().getName() + " is executing method2");
        try {
            Thread.sleep(1000); // Introducing delay to exaggerate the deadlock scenario
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resource.method1(this); // Method2 now calls method1 on resource1
    }
}

public class DeadlockDemo {
    public static void main(String[] args) {
        final SharedResource resource1 = new SharedResource();
        final SharedResource resource2 = new SharedResource();

        // Thread 1 acquires lock on resource1 and then tries to acquire lock on resource2
        Thread thread1 = new Thread(() -> resource1.method1(resource2));
        // Thread 2 acquires lock on resource2 and then tries to acquire lock on resource1
        Thread thread2 = new Thread(() -> resource2.method1(resource1));

        thread1.start(); // Start thread 1
        thread2.start(); // Start thread 2
    }
}
```

In this example:

- Two `SharedResource` instances are created, `resource1` and `resource2`.
- `method1` and `method2` are synchronized methods that acquire locks on the current `SharedResource` instance (`this`).
- `DeadlockDemo` creates two threads (`thread1` and `thread2`) that call `method1` on `resource1` and `resource2` respectively.
- Due to the synchronized nature of `method1` and `method2`, each thread will acquire a lock on one resource
and then try to acquire a lock on the other resource, leading to a deadlock scenario where both threads are waiting indefinitely
for each other to release the lock.

```java
// Example of Thread live lock.  

public class LiveLockExample {
	static class PoliteWorker {
		private String name;
		private boolean isPolite;
		private boolean hasBridgeLock = false;

		public PoliteWorker(String name, boolean isPolite) {
			this.name = name;
			this.isPolite = isPolite;
		}

		public String getName() {
			return name;
		}

		public boolean isPolite() {
			return isPolite;
		}

		public boolean hasBridgeLock() {
			return hasBridgeLock;
		}

		public void tryToTakeBridge(PoliteWorker other) {
			while (!hasBridgeLock()) {
				System.out.println(this.name + " is trying to take the bridge...");

				try {
					Thread.sleep(100); // Simulating some work
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (other.isPolite() && !other.hasBridgeLock()) {
					System.out.println(this.name + " is being polite and steps back for " + other.getName());
					continue; // Try again
				}

				// Try to acquire the bridge lock
				synchronized (this) {
					if (!other.hasBridgeLock() && hasBridgeLock()) {
						System.out.println(this.name + " successfully acquired the bridge!");
						hasBridgeLock = true;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		final PoliteWorker worker1 = new PoliteWorker("Worker 1", true);
		final PoliteWorker worker2 = new PoliteWorker("Worker 2", true);

		Thread thread1 = new Thread(() -> worker1.tryToTakeBridge(worker2));
		thread1.start();

		Thread thread2 = new Thread(() -> worker2.tryToTakeBridge(worker1));
		thread2.start();
	}
}


```
***
### Locking with Custom Objects in Java
[Back to Top](#Table-of-contents)
- **Using Regular Objects for Locking**: A regular `Object` can be used for locking by synchronizing on it.
- **Critical Section**: Ensure that the critical section is synchronized properly to avoid race conditions.
- **Thread Safety**: Use synchronization to make the counter operations thread-safe.

```java
class Counter {
    private int count = 0;
    private final Object lock = new Object(); // Regular Object for locking

    public void increment() {
        synchronized (lock) { // Synchronize on the lock object
            count++;
            System.out.println(Thread.currentThread().getName() + " increments count to " + count);
        }
    }

    public void decrement() {
        synchronized (lock) { // Synchronize on the lock object
            count--;
            System.out.println(Thread.currentThread().getName() + " decrements count to " + count);
        }
    }

    public int getCount() {
        synchronized (lock) { // Synchronize on the lock object
            return count;
        }
    }
}

public class CounterDemo {
    public static void main(String[] args) {
        Counter counter = new Counter();

        // Threads incrementing the counter
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                counter.increment();
            }
        });

        // Threads decrementing the counter
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                counter.decrement();
            }
        });

        thread1.start();
        thread2.start();
    }
}
```

In this example:
- The `Counter` class uses a regular `Object` named `lock` for synchronization.
- Methods `increment()`, `decrement()`, and `getCount()` are synchronized on `lock`.
- Each thread increments or decrements the counter in a synchronized manner, ensuring thread safety.
***
### Locks and ReentrantLocks in Java
[Back to Top](#Table-of-contents)
- **Locks**: Mechanism to control access to a shared resource by multiple threads.
- **ReentrantLock**: Implementation of the `Lock` interface that allows a thread to re-enter a critical section.

```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Example demonstrating the use of ReentrantLock
class Counter {
    private int count = 0;
    private final Lock lock = new ReentrantLock();

    public void increment() {
        lock.lock(); // Acquiring the lock
        try {
            count++;
        } finally {
            lock.unlock(); // Releasing the lock in a finally block
        }
    }

    public int getCount() {
        lock.lock(); // Acquiring the lock
        try {
            return count;
        } finally {
            lock.unlock(); // Releasing the lock in a finally block
        }
    }
}

public class ReentrantLockDemo {
    public static void main(String[] args) {
        final Counter counter = new Counter();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Count: " + counter.getCount());
    }
}
```

In this example:
- The `Counter` class uses a `ReentrantLock` to protect the `count` variable from being accessed concurrently by multiple threads.
- The `increment()` and `getCount()` methods acquire the lock using `lock.lock()` before performing operations and release it using `lock.unlock()` 
  in a `finally` block to ensure the lock is released even if an exception occurs.
- `ReentrantLock` allows a thread to enter the same critical section multiple times (reentrant behavior), which is not possible 
   with intrinsic locks (`synchronized` blocks/methods).
- The `main()` method creates two threads that concurrently increment the `Counter` instance and then prints the final count after both threads have completed.

***
### wait(), notify() and notifyAll() in Java
[Back to Top](#Table-of-contents)

- **wait()**: Causes the current thread to wait until another thread invokes `notify()` or `notifyAll()` on the same object.
- **notify()**: Wakes up a single thread that is waiting on the same object on which `notify()` is called.
- **notifyAll()**: Wakes up all threads that are waiting on the same object on which `notifyAll()` is called.

```java
class SharedResource {
    private int data;
    private boolean produced;

    // Producer thread method
    synchronized void produce(int value) {
        // Wait until data has been consumed
        while (produced) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        data = value;
        produced = true;
        System.out.println("Produced: " + data);
        notify(); // Notify the consumer that data is ready
    }

    // Consumer thread method
    synchronized int consume() {
        // Wait until data has been produced
        while (!produced) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        produced = false;
        System.out.println("Consumed: " + data);
        notify(); // Notify the producer that data has been consumed
        return data;
    }
}

public class WaitNotifyExample {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        // Producer thread
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                sharedResource.produce(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Consumer thread
        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                int value = sharedResource.consume();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();
    }
}
```

#### Explanation:
- **SharedResource**: This class represents a shared resource where the producer produces data and the consumer consumes it.
- **produce()**: This method is synchronized and used by the producer thread to produce data. It sets the `data` and notifies the consumer.
- **consume()**: This method is synchronized and used by the consumer thread to consume data. It waits until data is produced, consumes it, and notifies the producer.
- **wait() and notify()**: They are called within synchronized blocks to ensure that the current thread has acquired the intrinsic lock of the object.
  This is necessary because both `wait()` and `notify()` methods manipulate the state of the thread and the object's monitor.
***
### Why should wait() and notify() be called in synchronized blocks?
Yes, `wait()` and `notify()` should be called within synchronized blocks or methods. Here's why:

1. **Thread Safety**: Both `wait()` and `notify()` methods require the current thread to hold the intrinsic lock of the object on which they are called.
    This ensures that the thread is in a consistent state and prevents concurrent modification of the object's state.

2. **IllegalMonitorStateException**: If `wait()` or `notify()` is called without holding the lock of the object, it will result in an `IllegalMonitorStateException`.

3. **Race Conditions**: Calling `wait()` and `notify()` without synchronization can lead to race conditions where threads might miss signals or wake up unnecessarily.

By calling `wait()` and `notify()` within synchronized blocks, we ensure proper synchronization and prevent potential issues related to thread safety and race conditions.


***
### Difference between wait() and sleep() in Java
[Back to Top](#Table-of-contents)
- **wait()**:
    - Called on an object to pause the current thread until another thread invokes `notify()` or `notifyAll()` on the same object.
    - Releases the lock it holds.
    - Can only be called in synchronized context.

- **sleep()**:
    - Called on the `Thread` class to pause the execution of the current thread for a specified amount of time.
    - Does not release any locks it holds.
    - Can be called from anywhere in the code.

```java
class WaitSleepDemo {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread waitThread = new Thread(() -> {
            synchronized (lock) {
                System.out.println("WaitThread: Entered synchronized block");
                try {
                    lock.wait(); // Pauses and releases the lock
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("WaitThread: Resumed");
            }
        });

        Thread sleepThread = new Thread(() -> {
            synchronized (lock) {
                System.out.println("SleepThread: Entered synchronized block");
                try {
                    Thread.sleep(2000); // Pauses without releasing the lock
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("SleepThread: Resumed");
            }
        });

        waitThread.start();
        sleepThread.start();

        try {
            Thread.sleep(1000); // Giving time for threads to start
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (lock) {
            lock.notify(); // Notifying one of the waiting threads
        }
    }
}
```

In the example:
- `waitThread` waits using `lock.wait()`, which releases the lock.
- `sleepThread` sleeps using `Thread.sleep()`, which does not release the lock.
- The main thread notifies one of the waiting threads (`waitThread` or `sleepThread`) after a delay, allowing one of them to resume execution.

***
### Producer-Consumer Problem with Locks
[Back to Top](#Table-of-contents)

- **Producer**: Produces data and puts it into a shared buffer.
- **Consumer**: Consumes data from the shared buffer.
- **Locks**: Used to synchronize access to the shared buffer.

```java
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ProducerConsumer {
    private Queue<Integer> buffer = new LinkedList<>();
    private int capacity = 10;

    private Lock lock = new ReentrantLock();
    private Condition bufferNotFull = lock.newCondition();
    private Condition bufferNotEmpty = lock.newCondition();

    public void produce() throws InterruptedException {
        lock.lock();
        try {
            while (buffer.size() == capacity) {
                // Buffer is full, wait for consumers to consume
                // The producer wait for the bufferNotFull Signal from consumer 
                bufferNotFull.await();
            }
            int data = (int) (Math.random() * 100);
            buffer.offer(data);
            System.out.println("Produced " + data);
            // Signal consumers that buffer has data
            bufferNotEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public void consume() throws InterruptedException {
        lock.lock();
        try {
            while (buffer.isEmpty()) {
                // Buffer is empty, wait for producers to produce
                // Consumer awaits the buffer not empty Signal from Producer
                bufferNotEmpty.await();
            }
            int data = buffer.poll();
            System.out.println("Consumed " + data);
            // Signal producers that buffer is not full
            bufferNotFull.signal();
        } finally {
            lock.unlock();
        }
    }
}

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        ProducerConsumer producerConsumer = new ProducerConsumer();

        Thread producerThread = new Thread(() -> {
            try {
                while (true) {
                    producerConsumer.produce();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                while (true) {
                    producerConsumer.consume();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
```

This example demonstrates the Producer-Consumer problem using locks (`ReentrantLock`) and conditions (`Condition`). The `lock` ensures that only one thread can access the buffer at a time. `bufferNotFull` and `bufferNotEmpty` conditions are used to manage the state of the buffer:

- **Producer** waits when the buffer is full (`buffer.size() == capacity`) and notifies consumers when it produces an item.
- **Consumer** waits when the buffer is empty (`buffer.isEmpty()`) and notifies producers when it consumes an item.

This ensures that producers and consumers work in a synchronized manner, preventing issues like race conditions, deadlock, and starvation.
***
### Lock vs Synchronization
[Back to Top](#Table-of-contents)
- **Synchronization**:
    - **Intrinsic to Java**: Built-in mechanism provided by the Java language and JVM.
    - **Keyword**: `synchronized` keyword is used to mark critical sections of code.
    - **Lock Acquisition**: Automatically acquired and released by the JVM.
    - **Reentrant**: Supports reentrant behavior.
    - **Wait and Notify**: Built-in methods `wait()`, `notify()`, and `notifyAll()`.

- **Lock**:
    - **Interface**: `Lock` is an interface in `java.util.concurrent.locks` package.
    - **Classes**: Various implementations like `ReentrantLock`, `ReadWriteLock`, etc.
    - **Explicit**: Acquire and release is explicit, using `lock()` and `unlock()` methods.
    - **Condition Support**: Supports additional features like conditions (`Condition` interface).
    - **Flexibility**: More flexibility in acquiring and releasing locks, can handle more complex scenarios.

```java
// Example using synchronized keyword
class SynchronizedCounter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}

// Example using ReentrantLock
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LockCounter {
    private int count = 0;
    private Lock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
}

public class LockVsSyncDemo {
    public static void main(String[] args) throws InterruptedException {
        // Using synchronized keyword
        SynchronizedCounter synchronizedCounter = new SynchronizedCounter();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                synchronizedCounter.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                synchronizedCounter.increment();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Synchronized Counter: " + synchronizedCounter.getCount());

        // Using ReentrantLock
        LockCounter lockCounter = new LockCounter();
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                lockCounter.increment();
            }
        });
        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                lockCounter.increment();
            }
        });

        thread3.start();
        thread4.start();

        thread3.join();
        thread4.join();

        System.out.println("Lock Counter: " + lockCounter.getCount());
    }
}
```
***
### Using Lock and Condition Variables

```java
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// SharedResource class with Lock and Condition variables
class SharedResource {
    private final Lock lock = new ReentrantLock(); // Lock object
    private final Condition condition = lock.newCondition(); // Condition variable
    private boolean isDataAvailable = false;
    private int data;

    // Producer method
    void produce(int data) {
        lock.lock(); // Acquire the lock
        try {
            // Wait until data is consumed
            while (isDataAvailable) {
                condition.await(); // Release lock and wait for signal
            }
            this.data = data; // Produce data
            isDataAvailable = true; // Data is now available
            System.out.println("Produced data: " + data);
            condition.signal(); // Signal consumer thread
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // Release the lock
        }
    }

    // Consumer method
    int consume() {
        lock.lock(); // Acquire the lock
        int consumedData = 0;
        try {
            // Wait until data is produced
            while (!isDataAvailable) {
                condition.await(); // Release lock and wait for signal
            }
            consumedData = this.data; // Consume data
            isDataAvailable = false; // Data has been consumed
            System.out.println("Consumed data: " + consumedData);
            condition.signal(); // Signal producer thread
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // Release the lock
        }
        return consumedData;
    }
}

// Main class demonstrating Lock and Condition usage
public class LockConditionDemo {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        // Producer thread
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                resource.produce(i);
            }
        });

        // Consumer thread
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                int data = resource.consume();
            }
        });

        producer.start(); // Start producer thread
        consumer.start(); // Start consumer thread
    }
}
```
### Using Synchronization, wait, and notify

```java
// SharedResource class with intrinsic locking and wait/notify
class SharedResource {
    private boolean isDataAvailable = false;
    private int data;

    // Producer method
    synchronized void produce(int data) {
        try {
            // Wait until data is consumed
            while (isDataAvailable) {
                wait(); // Release lock and wait for notification
            }
            this.data = data; // Produce data
            isDataAvailable = true; // Data is now available
            System.out.println("Produced data: " + data);
            notify(); // Notify consumer thread
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Consumer method
    synchronized int consume() {
        int consumedData = 0;
        try {
            // Wait until data is produced
            while (!isDataAvailable) {
                wait(); // Release lock and wait for notification
            }
            consumedData = this.data; // Consume data
            isDataAvailable = false; // Data has been consumed
            System.out.println("Consumed data: " + consumedData);
            notify(); // Notify producer thread
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return consumedData;
    }
}

// Main class demonstrating intrinsic locking and wait/notify usage
public class WaitNotifyDemo {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        // Producer thread
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                resource.produce(i);
            }
        });

        // Consumer thread
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                int data = resource.consume();
            }
        });

        producer.start(); // Start producer thread
        consumer.start(); // Start consumer thread
    }
}
```

#### Comments Explanation:

#### SharedResource Class
- **Lock and Condition Variables**:
    - `Lock lock = new ReentrantLock();` - Create a ReentrantLock.
    - `Condition condition = lock.newCondition();` - Create a Condition variable associated with the lock.
    - `lock.lock();` - Acquire the lock.
    - `condition.await();` - Release the lock and wait for a signal.
    - `condition.signal();` - Signal the waiting thread to continue.
    - `lock.unlock();` - Release the lock.

- **Synchronization, wait, and notify**:
    - `synchronized void produce(int data)` - Method is synchronized, allowing only one thread at a time to execute it.
    - `wait();` - Release the lock and wait for notification.
    - `notify();` - Notify one waiting thread to continue.

#### Main Class
- Both examples create instances of `SharedResource` and use lambda expressions to define `Runnable` tasks for producer and consumer threads.
- `Thread.start()` is used to start the threads.

These examples illustrate how to use `Lock` and `Condition` variables and `synchronized`, `wait()`, and `notify()` for inter-thread communication and synchronization in Java, with detailed comments explaining each step.
*** 


***
### Thread Stack
[Back to Top](#Table-of-contents)
- **Individual Stack**: Each thread has its own stack, separate from other threads.
- **Stack Frames**: Contains method call frames, local variables, and partial results.
- **LIFO Order**: Operates in Last-In-First-Out order. New frames are pushed when methods are called and popped when methods return.
- **Local Variables**: Stores method-local variables and parameters.
- **Method Calls**: Each method call generates a new frame in the thread stack.
- **Stack Size**: Limited by the JVM; can lead to `StackOverflowError` if exceeded.
- **Thread Safety**: No direct sharing of data between stacks, making them inherently thread-safe for local variable access.

```java
public class ThreadStackDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                method1();
            }
        });
        thread.start(); // Start a new thread
    }

    public static void method1() {
        int localVar1 = 10; // Local variable in method1's stack frame
        method2();
    }

    public static void method2() {
        int localVar2 = 20; // Local variable in method2's stack frame
        // Stack grows as method calls are made
    }
}
```

***
### Heap Memory and Garbage Collection
[Back to Top](#Table-of-contents)
- **Heap Memory**: Shared memory area used for dynamic memory allocation. Objects created by threads are stored here.
- **Generations**: Divided into Young Generation, Old Generation, and Permanent Generation (MetaSpace in Java 8+).
    - **Young Generation**: Newly created objects, includes Eden Space and Survivor Spaces.
    - **Old Generation**: Objects that survived multiple garbage collections in Young Generation.
    - **Permanent Generation/MetaSpace**: Stores class metadata, methods, and constants.
- **Garbage Collection (GC)**: Automatic memory management by JVM, reclaiming memory of objects that are no longer in use.
    - **Minor GC**: Cleans Young Generation.
    - **Major GC**: Cleans Old Generation and may include Permanent Generation/MetaSpace.
- **GC Algorithms**:
    - **Serial GC**: Uses a single thread, suitable for small applications.
    - **Parallel GC**: Uses multiple threads, suitable for multi-threaded applications.
    - **CMS GC**: Concurrent Mark-Sweep, minimizes pause times.
    - **G1 GC**: Garbage First, suitable for large heap sizes.

### Troubleshooting Commands and Steps
[Back to Top](#Table-of-contents)
- **Monitoring Tools**:
    - `jps`: Lists Java processes.
    - `jstack`: Prints thread stack traces of a Java process.
    - `jmap`: Prints memory-related statistics of a Java process.
    - `jstat`: Monitors garbage collection and other statistics.
    - `jconsole`: Provides a graphical interface to monitor memory usage and GC activity.
    - `VisualVM`: Visual monitoring tool for tracking heap memory and GC.

- **Common JVM Options**:
    - `-Xms<size>`: Sets initial heap size.
    - `-Xmx<size>`: Sets maximum heap size.
    - `-XX:+PrintGCDetails`: Prints detailed GC logs.
    - `-XX:+UseG1GC`: Enables G1 garbage collector.
    - `-XX:HeapDumpPath=<path>`: Specifies path for heap dump on `OutOfMemoryError`.

***

```java
public class HeapMemoryDemo {
    public static void main(String[] args) {
        // Creating objects to allocate heap memory
        for (int i = 0; i < 100000; i++) {
            new Object();
        }

        // Suggesting JVM to run Garbage Collection
        System.gc();
    }
}
```

***

```bash
# Commands to troubleshoot memory issues

# List Java processes
jps

# Get thread stack traces
jstack <pid>

# Get heap memory usage
jmap -heap <pid>

# Monitor garbage collection statistics
jstat -gc <pid>

# Use jconsole for graphical monitoring
jconsole <pid>

# Start VisualVM for advanced monitoring
jvisualvm
```

***

### How to do a Heap Dump
[Back to Top](#Table-of-contents)
- **Heap Dump**: Snapshot of the heap memory of a Java process, used for analyzing memory usage and identifying memory leaks.

***

### Methods to Generate Heap Dump
[Back to Top](#Table-of-contents)
- **Using JVM Options**:
    - `-XX:+HeapDumpOnOutOfMemoryError`: Automatically generates a heap dump when an `OutOfMemoryError` occurs.
    - `-XX:HeapDumpPath=<path>`: Specifies the path where the heap dump file will be saved.

- **Using jmap Command**:
    - `jmap -dump:live,format=b,file=<file-path> <pid>`: Generates a heap dump of the live objects in the specified process.

- **Using JVisualVM**:
    - Open JVisualVM.
    - Connect to the target Java process.
    - Go to the "Monitor" tab.
    - Click on "Heap Dump".

- **Using JConsole**:
    - Open JConsole.
    - Connect to the target Java process.
    - Go to the "Memory" tab.
    - Click on "Dump Heap".

- **Programmatically**:
    - Using the `com.sun.management.HotSpotDiagnosticMXBean` API.

***

```java
import com.sun.management.HotSpotDiagnosticMXBean;
import java.lang.management.ManagementFactory;
import java.io.IOException;

public class HeapDumpUtil {

    private static final String HOTSPOT_BEAN_NAME = "com.sun.management:type=HotSpotDiagnostic";
    private static HotSpotDiagnosticMXBean hotspotMBean;

    static {
        hotspotMBean = ManagementFactory.newPlatformMXBeanProxy(
            ManagementFactory.getPlatformMBeanServer(), HOTSPOT_BEAN_NAME, HotSpotDiagnosticMXBean.class);
    }

    public static void dumpHeap(String filePath, boolean live) throws IOException {
        hotspotMBean.dumpHeap(filePath, live);
    }

    public static void main(String[] args) {
        try {
            // Specify the path where the heap dump file will be saved
            dumpHeap("heap_dump.hprof", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

***

```bash
# Generate a heap dump using jmap
# Replace <pid> with the process ID and <file-path> with the desired file path
jmap -dump:live,format=b,file=<file-path> <pid>

# Example:
jmap -dump:live,format=b,file=/path/to/heap_dump.hprof 12345
```

***

### Analyzing Heap Dump
[Back to Top](#Table-of-contents)
- **Eclipse Memory Analyzer (MAT)**:
    - Open the heap dump file in MAT.
    - Analyze memory usage and identify memory leaks.

- **VisualVM**:
    - Open the heap dump file in VisualVM.
    - Use the profiler to analyze memory allocation.

***

***

### Thread Local Storage
[Back to Top](#Table-of-contents)
- **ThreadLocal Class**: Provides thread-local variables.
- **Isolation**: Each thread accessing a `ThreadLocal` variable has its own, independently initialized copy.
- **Use Cases**: Useful for thread-specific data, such as user sessions or transaction contexts.
- **Methods**:
    - `initialValue()`: Provides initial value for the thread-local variable.
    - `get()`: Returns the current thread's value.
    - `set(T value)`: Sets the current thread's value.
    - `remove()`: Removes the current thread's value, useful to prevent memory leaks.

```java
public class ThreadLocalDemo {
    // Define a ThreadLocal variable
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0; // Initial value for each thread
        }
    };

    public static void main(String[] args) {
        // Create a Runnable that increments the ThreadLocal variable
        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                threadLocal.set(threadLocal.get() + 1);
                System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
            }
        };

        // Start two threads
        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");

        thread1.start();
        thread2.start();
    }
}
```

**Explanation**:
- **ThreadLocal Variable**: `threadLocal` provides a separate value for each thread.
- **initialValue()**: Method to set the initial value (0 in this case).
- **Increment Operation**: Each thread increments its own value of `threadLocal`.
- **Output**: Each thread prints its own thread-local value, independent of the other thread.

***
