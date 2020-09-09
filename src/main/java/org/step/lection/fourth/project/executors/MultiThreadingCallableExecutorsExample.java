package org.step.lection.fourth.project.executors;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.step.lection.fourth.project.executors.MultiThreadingRunnableExecutorsExample.RANDOM;

public class MultiThreadingCallableExecutorsExample {

    public static void main(String[] args) {
        Callable<Integer> callable = () -> {
            System.out.println(String.format("Current Thread is %s", Thread.currentThread().getName()));

            return RANDOM.nextInt(1000);
        };
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService cachedExecutorService = Executors.newCachedThreadPool();

        List<Future<Integer>> futures = newCachedThreadPool(callable, cachedExecutorService);

        cachedExecutorService.shutdown();

//        List<Future<Integer>> futures = newSingleThreadPoolExecutor(callable, executorService);

//        executorService.shutdown();

        futures
                .stream()
                .map(fut -> {
                    Integer integer = null;
                    try {
                        integer = fut.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return integer;
                })
                .forEach(System.out::println);
    }

    private static List<Future<Integer>> newSingleThreadPoolExecutor(Callable<Integer> callable, ExecutorService executorService) {
        Future<Integer> first = executorService.submit(callable);
        Future<Integer> second = executorService.submit(callable);
        Future<Integer> third = executorService.submit(callable);

        return List.of(first, second, third);
    }

    private static void newFixedThreadPoolExample(Callable<Integer> callable) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<Callable<Integer>> callableList = Stream.generate(() -> callable)
                .limit(1000)
                .collect(Collectors.toList());

        callableList
                .forEach(executorService::submit);

        executorService.shutdown();
    }

    private static List<Future<Integer>> newCachedThreadPool(Callable<Integer> callable, ExecutorService executorService) {

        List<Callable<Integer>> callableList = Stream.generate(() -> callable)
                .limit(1000)
                .collect(Collectors.toList());

        return callableList
                .stream()
                .map(executorService::submit)
                .collect(Collectors.toList());
    }
}
