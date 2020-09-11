package org.step.lection.fourth.project.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicExample {

    volatile int i = 0;

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        AtomicLong atomicLong = new AtomicLong();
        AtomicBoolean atomicBoolean = new AtomicBoolean();

        Callable<Integer> callable = atomicInteger::incrementAndGet;
    }
}
