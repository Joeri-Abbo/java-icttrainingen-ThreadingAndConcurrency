package com.skillsoft.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class CommonCounter {
    private AtomicInteger firstNum = new AtomicInteger(0);
    private AtomicInteger secondNum = new AtomicInteger(0);

    public void incrementCounter() {
        firstNum.incrementAndGet();
        secondNum.incrementAndGet();
    }

    public int getFirstNum() {
        return firstNum.get();
    }

    public int getSecondNum() {
        return secondNum.get();
    }
}
