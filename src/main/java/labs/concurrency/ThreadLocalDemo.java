package labs.concurrency;

public class ThreadLocalDemo {
    private static ThreadLocal<Integer> tLocal = ThreadLocal.withInitial(() -> 0);
    public static void main(String[] args) {

        Thread t1 = new Thread(new LocalTask(tLocal), "Thread 1");
        Thread t2 = new Thread(new LocalTask(tLocal), "Thread 2");
        t1.start();
        t2.start();



    }

}

class LocalTask implements Runnable{

    ThreadLocal tLocal;

    LocalTask(ThreadLocal local){

        tLocal = local;
    }

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + ": " + tLocal.get());
        int value = (int) (Math.random()*2000);
        tLocal.set( value);
        System.out.println(Thread.currentThread().getName() + ": " + tLocal.get());
    }
}