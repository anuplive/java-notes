package labs.concurrency;

public class TaskExecutorWaiterDemo {

    public static void main(String[] args) {
        Task task = new Task();
        Thread taskExecutor = new Thread(() -> {
            task.execute();
        }, "Task Executor");
        Thread taskWaiter = new Thread(() -> {
            task.waitForTask();
        }, "Task Waiter");
        taskExecutor.start();
        taskWaiter.start();
    }
}

class Task {
    boolean isTaskComplete;

    Task(){
        isTaskComplete = false;
    }
    public synchronized void waitForTask(){
        while (true){
            try{
                while (!isTaskComplete){
                    wait();
                }
                System.out.println(Thread.currentThread().getName() + " :Wait complete");
                isTaskComplete = false;
                notifyAll();
            }catch (InterruptedException ie){
                System.out.println(ie.getMessage());
            }
        }}

    public synchronized void execute(){
        while (true){
            try {
                while(isTaskComplete){
                    wait();
                }
                System.out.println(Thread.currentThread().getName() + ": Execution start");
                Thread.sleep(3000);
                isTaskComplete = true;
                System.out.println("Task done!");
                notifyAll();
            }catch(InterruptedException ie){
                System.out.println(ie.getMessage());}
        }
        }}


