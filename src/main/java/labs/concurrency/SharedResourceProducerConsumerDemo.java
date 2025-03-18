package labs.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedResourceProducerConsumerDemo {

    // Learnings
    // #1 The resource that should be threadsafe should have the locks defined in it


    // Producer
    // Consumer
    // SharedResource
    // Producer set a value waits to be Consumer: "Await Consumer: <value>"
    // Consumer consumes a value waits to be produced "Await Producer": <value>"
    // Producer Consumer interaction controlled using a lock

    public static void main(String[] args) {

        String[] sharedResource = new String[]{""};
        Lock resourceLock = new ReentrantLock();
        Condition readyToConsume = resourceLock.newCondition();
        Condition readyToProduce = resourceLock.newCondition();


        Thread producer = new Thread(() -> {
            while(true){
                try{
                    resourceLock.lock();
                    while(!sharedResource[0].equals("")){
                        readyToProduce.await();
                        System.out.println(Thread.currentThread().getName() + ": Waiting to Produce");
                    }
                    int message = (int) (Math.random() * 100);
                    sharedResource[0] = ("Message: " + message);
                    readyToConsume.signalAll();
                    Thread.sleep(800);
                    System.out.println("Produced Message:" + message);
                }catch(InterruptedException ie){
                    System.out.println(ie.getMessage());
                } finally{
                    resourceLock.unlock();
                }
        }
        });

        Thread consumer = new Thread(() -> {
            while(true){
                try{
                    resourceLock.lock();
                    while((sharedResource[0].equals(""))){
                        readyToConsume.await();
                        System.out.println(Thread.currentThread().getName() + ": Waiting to Consume");
                    }
                    String message = sharedResource[0];
                    sharedResource[0] = "";
                    readyToProduce.signalAll();
                    Thread.sleep(800);
                    System.out.println("Consumed message :" + message );
                }catch(InterruptedException ie){
                    System.out.println(ie.getMessage());
                } finally{
                    resourceLock.unlock();
                }
            }
        });

        producer.start();
        consumer.start();
    }

}




