package com.skillsoft.concurrency;

public class CommonCounter {
    private int firstNum = 0;
    private int secondNum = 0;

    public void incrementCounter() {
        synchronized (this) {
            firstNum++;
        }
        secondNum++;
    }

    public int getFirstNum() {
        return firstNum;
    }

    public int getSecondNum() {
        return secondNum;
    }
}
