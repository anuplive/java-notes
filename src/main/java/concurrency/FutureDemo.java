package concurrency;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class FutureDemo {

    public static void main (String args []) throws ExecutionException, InterruptedException{

        ExecutorService executorService = Executors.newFixedThreadPool(5);


        Future<List<Integer>> future = executorService.submit(()-> {
            // Long running process
            Thread.sleep(6*1000);
            return Arrays.asList(1,2,3,4,5);
        });
        System.out.println(future.get());

        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.complete("Sample Result");
        CompletableFuture.runAsync(()->{});
    }
}
