import java.sql.Time;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args){

        Resource sharedResource = new Resource();

        // Declare the Threads
        Thread t1 = new Thread(new Worker(sharedResource));
        Thread t2 = new Thread(new Worker(sharedResource));
        Thread t3 = new Thread(new Worker(sharedResource));
        Thread t4 = new Thread(new Worker(sharedResource));
        Thread t5 = new Thread(new Worker(sharedResource));

        // Start the Threads
        t1.start(); t2.start(); t3.start(); t4.start(); t5.start();

        // Join the Threads
        try{
            t1.join();t2.join();t3.join();;t4.join(); t5.join();
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }
}


class Resource{

    private final  int MAX_PERMITS = 3;
    Semaphore permits = new Semaphore(MAX_PERMITS);

    void useResource(){
        long start = 0L;
        try{
            permits.acquire();
            start = System.currentTimeMillis();
            System.out.println("Successfully Acquired resource ");
            Thread.sleep(10000);
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }finally{
            System.out.println("Release the resource after " + (System.currentTimeMillis() - start));
            permits.release();
        }
    }
}

class Worker implements Runnable{
    private Resource resource;
    Worker(Resource r){
        this.resource = r;
    }
    public void run(){
        resource.useResource();
    }
}