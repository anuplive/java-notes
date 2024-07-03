public class DeadLockExample {

    Object lock1 = new Object();
    Object lock2 = new Object();

    public void thread1Task(){
        System.out.println(Thread.currentThread().getName() + "doing thread1Task");
    }
    public void thread2Task(){
        System.out.println(Thread.currentThread().getName() + "doing thread2Task");
    }

    public static void main(String[] args){
        DeadLockExample de = new DeadLockExample();

        Thread t1 = new Thread(() -> {
            try{
                synchronized (de.lock1){
                    Thread.sleep(3000);
                    de.thread1Task();

                }

            }catch(InterruptedException ie){
                ie.printStackTrace();
            }
        });

    }


}
