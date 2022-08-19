package com.skillsoft.concurrency;


public class Synchronization {

    private static final int NUM_ITERATIONS = 100000;

    public static void main(String[] args) {
        CommonCounter commonCounter = new CommonCounter();

        Thread threadOne = new Thread(new CounterIncrementor(commonCounter, NUM_ITERATIONS));
        Thread threadTwo = new Thread(new CounterIncrementor(commonCounter, NUM_ITERATIONS));

        System.out.println("Start value of firstNum:" + commonCounter.getFirstNum());
        System.out.println("Start value of secondNum:" + commonCounter.getSecondNum());

        try {
            threadOne.start();
            threadTwo.start();

            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Start value of firstNum:" + commonCounter.getFirstNum());
        System.out.println("Start value of secondNum:" + commonCounter.getSecondNum());
    }
}
