package org.step.lection.fourth.project.stream.api.lambda;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExceptionLambdaExample {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Callable<String> callable = () -> {
            Thread.sleep(1000);

            return "abc";
        };
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        executorService.submit(runnable);

        if (!executorService.isShutdown()) {
            executorService.shutdown();
        }
    }
}
