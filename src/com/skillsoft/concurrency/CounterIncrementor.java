package com.skillsoft.concurrency;

public class CounterIncrementor implements Runnable {
    private CommonCounter myCounter;
    private int numIterations;

    public CounterIncrementor(CommonCounter commonCounter, int numIterations) {
        this.myCounter = commonCounter;
        this.numIterations = numIterations;
    }

    @Override
    public void run() {
        for (int i = 0; i < numIterations; i++) {
            myCounter.incrementCounter();
        }
    }
}
