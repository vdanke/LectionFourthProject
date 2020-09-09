package org.step.lection.fourth.project.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MultiThreadingRunnableExecutorsExample {

    static final Random RANDOM = new Random();

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println(
                String.format("Runnable in Thread %s", Thread.currentThread().getName())
        );

//        newSingleThreadPoolExecutorExample(runnable);

//        newFixedThreadPoolExample(runnable);

        newCachedThreadPool(runnable);
    }

    private static void newSingleThreadPoolExecutorExample(Runnable runnable) {
        // Executor - состоящий из пула одного потока
        ExecutorService executorService = Executors.newSingleThreadExecutor();

//        executorService.execute(runnable);
        executorService.submit(runnable); // 1
        executorService.submit(runnable); // 2
        executorService.submit(runnable); // 3

        executorService.shutdown();
    }

    private static void newFixedThreadPoolExample(Runnable runnable) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<Runnable> runnableList = Stream.generate(() -> runnable)
                .limit(1000)
                .collect(Collectors.toList());

        runnableList
                .forEach(executorService::submit);

        executorService.shutdown();
    }

    private static void newCachedThreadPool(Runnable runnable) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        List<Runnable> runnableList = Stream.generate(() -> runnable)
                .limit(1000)
                .collect(Collectors.toList());

        runnableList
                .forEach(executorService::submit);

        executorService.shutdown();
    }
}
