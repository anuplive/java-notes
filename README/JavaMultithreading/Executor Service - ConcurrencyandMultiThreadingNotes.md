
#### Why to Use Thread Pools
[Back to Top](#Table-of-contents)
- **Efficient Resource Management**: Thread pools manage threads efficiently by reusing them.
- **Improved Performance**: Reduces the overhead of thread creation and termination.
- **Better Scalability**: Limits the number of concurrent threads and manages their execution.
- **Task Queueing**: Handles task submission and executes them as soon as a thread is available.
- **Thread Reuse**: Allows threads to be reused for multiple tasks.

***

#### ExecutorService in Java
[Back to Top](#Table-of-contents)
- **Thread Pool Management**: ExecutorService manages a pool of threads for executing tasks asynchronously.
- **Types**: Provides different implementations like `ThreadPoolExecutor`, `ScheduledThreadPoolExecutor`, etc.
- **Task Submission**: Accepts tasks for execution via `execute(Runnable task)` or `submit(Callable task)`.
- **Thread Reuse**: Reuses threads from the pool, avoiding the overhead of creating new threads.
- **Lifecycle Management**: Handles thread lifecycle automatically.
- **Task Queue**: Manages a queue to hold tasks waiting to be executed.
- **Exception Handling**: Propagates exceptions thrown by tasks to the caller.
- **Shutdown**: Gracefully shuts down the ExecutorService, allowing currently executing tasks to complete.

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceDemo {
    public static void main(String[] args) {
        // Creating a thread pool with fixed number of threads
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Submitting tasks for execution
        executor.submit(() -> {
            System.out.println("Task 1 executed by thread: " + Thread.currentThread().getName());
        });

        executor.submit(() -> {
            System.out.println("Task 2 executed by thread: " + Thread.currentThread().getName());
        });

        // Shutting down the executor service
        executor.shutdown();
    }
}
```
***

#### SingleThreadExecutorService
[Back to Top](#Table-of-contents)
- **SingleThreadExecutorService**: Executor service that uses a single worker thread operating off an unbounded queue, which execute tasks sequentially.

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorServiceDemo {
    public static void main(String[] args) {
        // Creating a SingleThreadExecutor
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Submitting tasks to the executor
        executor.submit(() -> {
            System.out.println("Task 1 executed by " + Thread.currentThread().getName());
        });

        executor.submit(() -> {
            System.out.println("Task 2 executed by " + Thread.currentThread().getName());
        });

        executor.submit(() -> {
            System.out.println("Task 3 executed by " + Thread.currentThread().getName());
        });

        // Gracefully shutting down the executor
        executor.shutdown(); // Initiates graceful shutdown

        try {
            // Wait for all tasks to complete or until the timeout occurs
            if (!executor.awaitTermination(5000, java.util.concurrent.TimeUnit.MILLISECONDS)) {
                // Executor still has tasks pending after timeout
                executor.shutdownNow(); // Interrupt the executing threads forcefully
            }
        } catch (InterruptedException e) {
            executor.shutdownNow(); // (Re-)Cancel if current thread also interrupted
            Thread.currentThread().interrupt(); // Preserve interrupt status
        }
    }
}
```

### Explanation:

- **Creating the Executor**: `ExecutorService executor = Executors.newSingleThreadExecutor();` creates a single-threaded executor service.

- **Submitting Tasks**: Tasks are submitted using `executor.submit(() -> { /* task code */ });`. Each task is a lambda expression that prints a message including the current thread name.

- **Graceful Shutdown**:
    - `executor.shutdown();` initiates a graceful shutdown of the executor service. It allows previously submitted tasks to execute before terminating.
    - `executor.awaitTermination(5000, TimeUnit.MILLISECONDS);` waits up to 5000 milliseconds for the executor to terminate. If the timeout is exceeded and tasks are still running, it returns `false`.
    - `executor.shutdownNow();` interrupts the executing tasks forcefully if the timeout is exceeded.
    - `Thread.currentThread().interrupt();` preserves the interrupt status of the current thread.

This ensures that all submitted tasks are executed and the executor service is shut down gracefully, handling interruptions and pending tasks appropriately.
***

#### FixedThreadPoolExecutor !!
[Back to Top](#Table-of-contents)
- **FixedThreadPoolExecutor**: A thread pool with a fixed number of threads.

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedThreadPoolExecutorDemo {
    public static void main(String[] args) {
        // Create a fixed thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit tasks to the thread pool
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " started");
                try {
                    Thread.sleep(2000); // Simulating some task
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task " + taskId + " completed");
            });
        }

        // Initiates an orderly shutdown of the executor
        executor.shutdown();

        try {
            // Wait for all tasks to complete or for 5 seconds (whichever comes first)
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Gracefully shut down the executor
        if (!executor.isTerminated()) {
            executor.shutdownNow(); // Cancel currently executing tasks
        }
    }
}
```
***
Sure, here's the updated section with comments and graceful shutdown using `awaitTermination`:

***
#### ScheduledExecutor !!
[Back to Top](#Table-of-contents)
- **ScheduledExecutorService**: Executes tasks periodically or after a delay.
- **schedule**: Executes a task after a delay.
- **scheduleAtFixedRate**: Executes a task periodically.
- **scheduleWithFixedDelay**: Executes a task periodically with a fixed delay between the termination of one execution and the commencement of the next.

```java
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorDemo {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        // Task to be executed after a delay
        executor.schedule(() -> {
            System.out.println("Scheduled task executed after 2 seconds");
        }, 2, TimeUnit.SECONDS);

        // Task to be executed periodically
        executor.scheduleAtFixedRate(() -> {
            System.out.println("Scheduled task executed every 3 seconds");
        }, 0, 3, TimeUnit.SECONDS);

        // Task to be executed periodically with a fixed delay
        executor.scheduleWithFixedDelay(() -> {
            System.out.println("Scheduled task executed with a fixed delay of 4 seconds");
        }, 0, 4, TimeUnit.SECONDS);

        // Graceful shutdown
        executor.awaitTermination(10, TimeUnit.SECONDS); // Wait for all tasks to finish
        executor.shutdown(); // Shutdown the executor
    }
}
```

**Explanation**:
- **ScheduledExecutorService**: Interface for scheduled execution of tasks.
- **schedule**: Executes a task after a specified delay.
- **scheduleAtFixedRate**: Executes a task periodically based on an initial delay and a fixed rate.
- **scheduleWithFixedDelay**: Executes a task periodically with a fixed delay between the end of one execution and the start of the next.
- **awaitTermination**: Waits for the executor to terminate, allowing time for remaining tasks to complete.
- **shutdown**: Initiates an orderly shutdown in which previously submitted tasks are executed, but no new tasks will be accepted.

***

#### Stopping Executors !!
[Back to Top](#Table-of-contents)

When using executors in Java, it's important to properly shut them down to release resources and ensure orderly shutdown of threads.

- **shutdown()**: Initiates an orderly shutdown in which previously submitted tasks are executed, but no new tasks will be accepted.
- **shutdownNow()**: Attempts to stop all actively executing tasks, halts the processing of waiting tasks, and returns a list of the tasks that were awaiting execution.
- **awaitTermination()**: Blocks until all tasks have completed execution after a shutdown request, or the timeout occurs, or the current thread is interrupted, whichever happens first.

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorShutdownDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Submit tasks
        executor.submit(() -> {
            System.out.println("Task 1 running");
        });
        executor.submit(() -> {
            System.out.println("Task 2 running");
        });

        // Initiates shutdown
        executor.shutdown();

        try {
            // Wait for existing tasks to terminate
            if (!executor.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                // Cancel currently executing tasks
                executor.shutdownNow();
                // Wait for tasks to respond to being cancelled
                if (!executor.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    System.err.println("Executor did not terminate");
                }
            }
        } catch (InterruptedException e) {
            // (Re-)Cancel if current thread also interrupted
            executor.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }
}
```
***

#### Callable Interface and Future
[Back to Top](#Table-of-contents)
- **Callable Interface**: Represents a task that returns a result and may throw an exception.
- **Future Interface**: Represents the result of an asynchronous computation.

```java
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MyCallable implements Callable<String> {
    public String call() {
        return "Callable thread is running";
    }
}

public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new MyCallable());

        try {
            // Retrieving the result of the callable thread
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown(); // Shutting down the executor service
    }
}
```

**Explanation**:
- `Callable` Interface: It's a functional interface that contains the `call()` method which returns a result.
- `Future` Interface: Represents the result of an asynchronous computation. It provides methods to check if the computation is done and to retrieve the result.
- `ExecutorService`: Provides methods to manage and control the execution of threads.
- `executor.submit(new MyCallable())`: Submits a `Callable` task for execution and returns a `Future` representing the pending result of the task.
- `future.get()`: Waits if necessary for the computation to complete, and then retrieves its result.
***

#### Callable Interface and Future Example with Multiple Tasks
[Back to Top](#Table-of-contents)

- **Callable Interface**: Represents a task that returns a result and may throw an exception.
- **Future Interface**: Represents the result of an asynchronous computation.

```java
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// Task implementing Callable interface
class MyCallable implements Callable<String> {
    private String name;

    public MyCallable(String name) {
        this.name = name;
    }

    public String call() {
        try {
            Thread.sleep(2000); // Simulating a time-consuming task
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Callable thread " + name + " is running";
    }
}

public class CallableFutureMultipleTasks {
    public static void main(String[] args) {
        // Creating an ExecutorService with multiple threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // List to hold Future objects
        List<Future<String>> futures = new ArrayList<>();

        // Submitting multiple tasks
        for (int i = 1; i <= 5; i++) {
            Callable<String> callable = new MyCallable("Task-" + i);
            Future<String> future = executor.submit(callable);
            futures.add(future);
        }

        // Retrieving results from Future objects
        for (Future<String> future : futures) {
            try {
                // Waiting for the task to complete and retrieving the result
                String result = future.get();
                System.out.println(result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        // Properly shutting down the ExecutorService
        executor.shutdown();
    }
}
```

##### Explanation:
- **MyCallable class**: Implements the `Callable` interface with a `call()` method that simulates a time-consuming task and returns a `String`.
- **CallableFutureMultipleTasks class**:
  - **ExecutorService**: Created using `Executors.newFixedThreadPool(3)` to manage and execute multiple tasks concurrently.
  - **List\<Future\<String\>\> futures**: Holds the `Future` objects representing the result of each asynchronous computation.
  - **for loop**: Submits multiple tasks (`MyCallable` instances) to the executor and adds their `Future` objects to the `futures` list.
  - **for-each loop**: Iterates over the `futures` list to wait for each task to complete and retrieve its result using `future.get()`.
  - **executor.shutdown()**: Initiates an orderly shutdown of the ExecutorService after all tasks are completed.
***
