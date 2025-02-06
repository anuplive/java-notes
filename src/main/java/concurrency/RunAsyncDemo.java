package concurrency;

import java.io.File;
import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RunAsyncDemo {


    public void savePerson(File personFile){

        ObjectMapper mapper = new ObjectMapper();
        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run(){

            }
        });

    }
}
