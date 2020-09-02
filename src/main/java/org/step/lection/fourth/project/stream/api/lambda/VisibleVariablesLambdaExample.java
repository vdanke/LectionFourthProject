package org.step.lection.fourth.project.stream.api.lambda;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class VisibleVariablesLambdaExample {

    private static final int i = 0;

    public static void main(String[] args) {
        int three = 3;
        int five = 5;

//        System.out.println(three++);
//        System.out.println(++three);

        Runnable first = getMessage(true, three);
        Runnable second = getMessage(false, five);

        new Thread(first).start();
        new Thread(second).start();

        Callable<String> callable = () -> UUID.randomUUID().toString().substring(1, 15);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Future<String> submit = executorService.submit(callable);

//        String s = submit.get();

//        Comparator<Integer> integerComparator = (first, second) -> {
//            return Integer.compare(first, second);
//        };
    }

//    public void showSomething() {
//        Runnable runnable = () -> {
//            int i = this.i;
//        };
//    }

    private static Runnable getMessage(boolean flag, int count) {
        // Effective final
        List<Integer> integerList = new ArrayList<>();
//        integerList.forEach(System.out::println);
//        int i1 = count++;
        final int finalVariable = 1;
        return () -> {
            if (flag) {
                System.out.println(finalVariable);
                System.out.println(flag);
//                integerList = new LinkedList<>();
                int mirror = count;
                for (int i = 0; i < count; i++) {
                    mirror++;
                    integerList.add(i);
                    System.out.println(i);
                }
            } else {
                System.out.println("Flag is false");
            }
        };
    }
}
