
#### Volatile in Java !!
[Back to Top](#Table-of-contents)
- **Volatile Keyword**: Ensures that the value of a variable is always read from and written to main memory, not from thread's cache.
- **Use Cases**: Used when a variable's value can be modified by multiple threads.
- **Visibility**: Ensures visibility of changes to variables across threads.
- **No Atomicity**: Does not provide atomicity like synchronized blocks.

```java
public class VolatileExample {
    private static volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            while (!flag) {
                // Spin until flag is true
            }
            System.out.println("Thread 1: Flag is now true");
        });

        Thread thread2 = new Thread(() -> {
            flag = true; // Changes flag value
            System.out.println("Thread 2: Flag is set to true");
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
```

- **Explanation**:
  - The `volatile` keyword ensures that the `flag` variable is always read from and written to main memory.
  - In this example, `thread1` spins until `flag` is true, because without `volatile`, it may cache the value of `flag`.
  - `thread2` sets `flag` to true. This change is immediately visible to `thread1` due to the `volatile` keyword.

***
Sure, here's the information formatted for a readme:

---

#### Stopping a Thread in Java
[Back to Top](#Table-of-contents)

- **Interrupting a Thread**: Use `interrupt()` method to interrupt the thread.
- **Using a flag to stop the thread**: Use a boolean flag to signal the thread to stop.
- **Using `Thread.stop()` method (deprecated)**: Unsafe method, should not be used.

```java
// Interrupting a Thread
class MyThread extends Thread {
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Thread is running");
            try {
                Thread.sleep(1000); // Simulating some work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Reset interrupted status
            }
        }
        System.out.println("Thread interrupted, exiting...");
    }
}

public class InterruptThreadDemo {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start();

        // Interrupt the thread after some time
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt(); // Interrupting the thread
    }
}

// Using a flag to stop the thread
class MyRunnable implements Runnable {
    private volatile boolean flag = false;

    public void run() {
        while (!flag) {
            System.out.println("Thread is running");
            try {
                Thread.sleep(1000); // Simulating some work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Reset interrupted status
            }
        }
        System.out.println("Thread stopped by flag, exiting...");
    }

    public void stopThread() {
        flag = true;
    }
}

public class StopThreadByFlagDemo {
    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();
        Thread t2 = new Thread(runnable);
        t2.start();

        // Stop the thread after some time
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runnable.stopThread(); // Stopping the thread by setting flag
    }
}
```


- Remember to handle InterruptedException properly and to use safe practices when stopping threads, especially avoiding the deprecated `Thread.stop()` method.
***

Certainly! Here are the commented versions of the Deadlock and Livelock examples:

---

#### Deadlock Example
[Back to Top](#Table-of-contents)

```java
public class DeadlockExample {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {  // Acquires lock1
                System.out.println("Thread 1: Holding lock 1...");

                try {
                    Thread.sleep(100);  // Simulating some task
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 1: Waiting for lock 2...");
                synchronized (lock2) {  // Tries to acquire lock2
                    System.out.println("Thread 1: Holding lock 1 and lock 2...");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {  // Acquires lock2
                System.out.println("Thread 2: Holding lock 2...");

                try {
                    Thread.sleep(100);  // Simulating some task
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 2: Waiting for lock 1...");
                synchronized (lock1) {  // Tries to acquire lock1
                    System.out.println("Thread 2: Holding lock 1 and lock 2...");
                }
            }
        });

        thread1.start();  // Start thread1
        thread2.start();  // Start thread2
    }
}
```

**Explanation**:
- In this example, `thread1` and `thread2` both acquire a lock and then try to acquire another lock that the other thread holds.
- This creates a circular wait condition, where both threads are waiting indefinitely for each other, causing a deadlock.

***

#### Livelock Example
[Back to Top](#Table-of-contents)

```java
public class LivelockExample {
    static class Spoon {
        private Diner owner;

        public Spoon(Diner owner) {
            this.owner = owner;
        }

        // Method to let the owner eat
        public synchronized void use() {
            System.out.printf("%s has eaten!\n", owner.name);
        }

        // Method to set the owner of the spoon
        public synchronized void setOwner(Diner diner) {
            this.owner = diner;
        }

        // Method to get the owner of the spoon
        public Diner getOwner() {
            return owner;
        }
    }

    static class Diner {
        private String name;
        private boolean isHungry;

        public Diner(String name) {
            this.name = name;
            this.isHungry = true;
        }

        // Method to eat with a spoon and another diner
        public void eatWith(Spoon spoon, Diner spouse) {
            while (isHungry) {
                // Check if the spoon's owner is not the current diner
                if (spoon.getOwner() != this) {
                    try {
                        Thread.sleep(1); // Give some time to the other diner
                    } catch (InterruptedException e) {
                        continue;
                    }
                    continue; // Retry
                }

                // The spouse is not hungry, so give the spoon to them
                if (!spouse.isHungry) {
                    System.out.println(name + ": You eat first my darling " + spouse.name + " !");
                    spoon.setOwner(spouse); // Pass the spoon to spouse
                    continue; // Retry
                }

                // Eat
                spoon.use(); // Use the spoon
                isHungry = false; // No longer hungry
                System.out.println(name + ": I'm stuffed, my dear " + spouse.name + " !");
                spoon.setOwner(spouse); // Pass the spoon to spouse
            }
        }
    }

    public static void main(String[] args) {
        Diner husband = new Diner("Bob");
        Diner wife = new Diner("Alice");

        Spoon spoon = new Spoon(husband); // Spoon initially with husband

        // Thread simulating husband
        new Thread(() -> husband.eatWith(spoon, wife)).start();

        // Thread simulating wife
        new Thread(() -> wife.eatWith(spoon, husband)).start();
    }
}
```

**Explanation**:
- In this simplified example, `husband` and `wife` are diners that share a `spoon`.
- They both use the `Spoon` object's `use()` and `setOwner()` methods in a way that can lead to a livelock.
- If one diner is not hungry (`!spouse.isHungry`), they pass the `spoon` to the other diner (`spouse`), which can lead to a situation where neither diner ever gets to eat because they are constantly passing the `spoon` back and forth.

This example illustrates a livelock where the diners are responsive to each other's state (whether they are hungry or not) but are unable to make progress towards their goal (eating) due to their shared interaction pattern.
***
Certainly! Here's a summary and commented code example for Atomic Variables in Java:

---
#### Atomic Variables
[Back to Top](#Table-of-contents)
- **Atomic Variables**: Variables that provide atomic operations without needing explicit synchronization.
- **Classes**: `AtomicInteger`, `AtomicLong`, `AtomicBoolean`, etc.
- **Methods**: `get()`, `set()`, `getAndSet()`, `incrementAndGet()`, `compareAndSet()`, etc.
- **Usage**: Useful in scenarios requiring thread-safe operations with minimal locking.

```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariablesDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);

        // Increment and get
        System.out.println("Initial value: " + atomicInteger.get());
        System.out.println("Incremented value: " + atomicInteger.incrementAndGet());

        // Compare and set if current value is 1
        boolean exchanged = atomicInteger.compareAndSet(1, 2);
        if (exchanged) {
            System.out.println("Value was successfully changed");
        } else {
            System.out.println("Value was not changed");
        }
    }
}
```

**Explanation**:
- `AtomicInteger atomicInteger = new AtomicInteger(0);`: Creates a new `AtomicInteger` with an initial value of 0.
- `atomicInteger.get()`: Retrieves the current value of the atomic integer.
- `atomicInteger.incrementAndGet()`: Atomically increments the current value by one and returns the updated value.
- `atomicInteger.compareAndSet(1, 2)`: Atomically compares the current value with 1; if true, sets the value to 2.

These operations are atomic and thread-safe, suitable for use in high-concurrency scenarios where synchronization overhead needs to be minimized.
***

#### Semaphores in Java
[Back to Top](#Table-of-contents)
- **Semaphores** are used to control access to resources in a concurrent environment.
- They maintain a set of permits. Each `acquire()` blocks if necessary until a permit is available.
- Each `release()` adds a permit, potentially releasing a blocking acquirer.

*** 

#### Semaphore Methods
- **`acquire()`**: Acquires a permit from this semaphore, blocking until one is available.
- **`release()`**: Releases a permit, returning it to the semaphore.
- **`availablePermits()`**: Returns the current number of permits available.
- **`tryAcquire()`**: Acquires a permit only if one is available at the time of invocation.
- **`tryAcquire(long timeout, TimeUnit unit)`**: Acquires a permit if one becomes available within the given waiting time.

```java
import java.util.concurrent.Semaphore;

class SharedResource {
    private static final Semaphore semaphore = new Semaphore(1);

    public void useResource() {
        try {
            semaphore.acquire(); // Acquiring the permit
            System.out.println("Resource being used by Thread: " + Thread.currentThread().getName());
            Thread.sleep(2000); // Simulating resource usage
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(); // Releasing the permit
        }
    }
}

public class SemaphoreDemo {
    public static void main(String[] args) {
        final SharedResource resource = new SharedResource();

        // Creating threads to use the shared resource
        Thread thread1 = new Thread(() -> {
            resource.useResource();
        }, "Thread 1");

        Thread thread2 = new Thread(() -> {
            resource.useResource();
        }, "Thread 2");

        thread1.start();
        thread2.start();
    }
}
```

**Explanation**:
- `Semaphore semaphore = new Semaphore(1);`: Creates a semaphore with one permit.
- `semaphore.acquire();`: Blocks the thread until a permit is available, then acquires it.
- `semaphore.release();`: Releases the permit back to the semaphore.
- `Thread.sleep(2000);`: Simulates the use of a resource.
- `semaphore.availablePermits();`: Returns the number of permits available.

***

#### Mutex and Semaphores
[Back to Top](#Table-of-contents)
- **Mutex**: **Mutex** (Mutual Exclusion Object) is a synchronization primitive that grants exclusive access to a resource. It ensures that only one thread can execute a critical section of code at a time.
- **Semaphores**: **Semaphores** are a more generalized synchronization primitive that can restrict the number of concurrent threads that are allowed to access a resource.

#### Mutex Example !!
[Back to Top](#Table-of-contents)
```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MutexExample {
    private static final Lock lock = new ReentrantLock();

    public void criticalSection() {
        lock.lock(); // Acquire the lock
        try {
            // Critical section
            System.out.println(Thread.currentThread().getName() + " is executing critical section");
        } finally {
            lock.unlock(); // Release the lock
        }
    }

    public static void main(String[] args) {
        MutexExample example = new MutexExample();

        // Creating threads to execute critical section
        Thread thread1 = new Thread(() -> example.criticalSection());
        Thread thread2 = new Thread(() -> example.criticalSection());

        thread1.start();
        thread2.start();
    }
}
```


#### Semaphores Example !!
[Back to Top](#Table-of-contents)
```java
import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    private static final Semaphore semaphore = new Semaphore(1); // Semaphore with 1 permit

    public void accessResource() {
        try {
            semaphore.acquire(); // Acquire the semaphore
            // Access the shared resource
            System.out.println(Thread.currentThread().getName() + " is accessing the resource");
            Thread.sleep(1000); // Simulating some task
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(); // Release the semaphore
        }
    }

    public static void main(String[] args) {
        SemaphoreExample example = new SemaphoreExample();

        // Creating threads to access the resource
        Thread thread1 = new Thread(() -> example.accessResource());
        Thread thread2 = new Thread(() -> example.accessResource());

        thread1.start();
        thread2.start();
    }
}
```

***

### Difference between Mutex and Semaphores
[Back to Top](#Table-of-contents)
- **Mutex**:
  - **Purpose**: Provides mutual exclusion to shared resources.
  - **Usage**: Typically used to protect critical sections of code.
  - **State**: Binary (locked/unlocked).
  - **Operation**: Acquired by a thread that wants exclusive access; only the thread holding the mutex can release it.

- **Semaphores**:
  - **Purpose**: Controls access to a pool of resources.
  - **Usage**: Used to control access to resources that are shared among multiple threads.
  - **State**: Integer value that can be decremented (P operation) or incremented (V operation).
  - **Operation**: Allows multiple threads to access the resource simultaneously up to a maximum defined limit.

***

#### Semaphore Example
[Back to Top](#Table-of-contents)

- **Semaphore**: Controls access to a shared resource through the use of a counter.
- **Acquire**: Decrements the counter and waits if the counter is zero.
- **Release**: Increments the counter.

```java
import java.util.concurrent.Semaphore;

class SharedResource {
    private static final int MAX_PERMITS = 3; // Maximum permits allowed
    private Semaphore semaphore = new Semaphore(MAX_PERMITS);

    public void useResource() {
        try {
            semaphore.acquire(); // Acquire a permit
            System.out.println("Resource used by Thread: " + Thread.currentThread().getName());
            Thread.sleep(1000); // Simulating some resource usage
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(); // Release the permit
        }
    }
}

class WorkerThread extends Thread {
    private SharedResource resource;

    public WorkerThread(SharedResource resource) {
        this.resource = resource;
    }

    public void run() {
        resource.useResource();
    }
}

public class SemaphoreExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        // Creating worker threads
        Thread t1 = new WorkerThread(resource);
        Thread t2 = new WorkerThread(resource);
        Thread t3 = new WorkerThread(resource);
        Thread t4 = new WorkerThread(resource);

        // Starting threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
```

**Explanation**:
- **Semaphore Initialization**: `Semaphore semaphore = new Semaphore(MAX_PERMITS);` initializes a semaphore with a maximum of 3 permits.
- **Acquiring and Releasing Permits**: In the `useResource()` method, `semaphore.acquire()` is called to acquire a permit, and `semaphore.release()` is called to release it after usage.
- **Worker Threads**: `WorkerThread` class represents threads that use the shared resource `resource`.
- **Main Method**: Creates four `WorkerThread` instances and starts them, demonstrating semaphore usage by limiting access to the shared resource to 3 threads at a time.

***

#### Mutex vs Binary Semaphore
[Back to Top](#Table-of-contents)

- **Mutex**:
  - Used for mutual exclusion.
  - Ensures that only one thread can execute the protected code segment at any time.
  - Typically used to protect critical sections where only one thread should enter at a time.

- **Binary Semaphore**:
  - Also used for mutual exclusion.
  - Has an internal counter that can be either 0 or 1.
  - Acts like a mutex but allows more flexibility in usage scenarios.

```java
import java.util.concurrent.Semaphore;

// Mutex example using synchronized block
class MutexExample {
    private final Object lock = new Object();

    public void criticalSection() {
        synchronized (lock) {
            // Critical section - Only one thread can execute this at a time
            System.out.println(Thread.currentThread().getName() + " is executing critical section.");
            try {
                Thread.sleep(1000); // Simulating some work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MutexExample example = new MutexExample();

        // Creating multiple threads to access critical section
        Thread t1 = new Thread(() -> example.criticalSection(), "Thread 1");
        Thread t2 = new Thread(() -> example.criticalSection(), "Thread 2");

        t1.start();
        t2.start();
    }
}

// Binary Semaphore example
class BinarySemaphoreExample {
    private Semaphore semaphore = new Semaphore(1); // Binary semaphore

    public void criticalSection() {
        try {
            semaphore.acquire(); // Acquiring the semaphore
            // Critical section - Only one thread can execute this at a time
            System.out.println(Thread.currentThread().getName() + " is executing critical section.");
            Thread.sleep(1000); // Simulating some work
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(); // Releasing the semaphore
        }
    }

    public static void main(String[] args) {
        BinarySemaphoreExample example = new BinarySemaphoreExample();

        // Creating multiple threads to access critical section
        Thread t1 = new Thread(() -> example.criticalSection(), "Thread 1");
        Thread t2 = new Thread(() -> example.criticalSection(), "Thread 2");

        t1.start();
        t2.start();
    }
}
```

**Explanation:**

- **MutexExample**:
  - Uses a synchronized block to achieve mutual exclusion. Only one thread can execute the critical section at a time.
  - `synchronized (lock)` ensures that when one thread enters `criticalSection()`, another thread must wait until the first thread exits.

- **BinarySemaphoreExample**:
  - Uses `Semaphore` with a permit count of 1, effectively making it a binary semaphore.
  - `semaphore.acquire()` ensures that only one thread can acquire the permit (enter the critical section) at a time.
  - `semaphore.release()` releases the permit after the critical section is executed, allowing another thread to acquire it.

Both examples achieve mutual exclusion, but `Semaphore` offers more flexibility, such as the ability to acquire multiple permits and timed waits.
***
