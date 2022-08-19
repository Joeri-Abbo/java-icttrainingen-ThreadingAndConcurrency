package com.skillsoft.concurrency;

public class ThreadLifeCycle {
    public static class Walk implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i < 5; i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("I am walking... My isAlive state is " + Thread.currentThread().isAlive());
            }
        }
    }

    public static class ChewGum implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i < 5; i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("I am chewing gum...");
            }
        }
    }

    public static void main(String[] args) {
        Thread walkThread = new Thread(new Walk());
        System.out.println("isAlive state of walkThread after init: " + walkThread.isAlive());
        try {
            walkThread.start();
            System.out.println("IsAlive state of walkThread after start " + Thread.currentThread().isAlive());
            walkThread.join(5000);
            System.out.println("IsAlive state of walkThread after join " + Thread.currentThread().isAlive());

            Thread.sleep(10000);
            System.out.println("IsAlive state of walkThread at the end " + Thread.currentThread().isAlive());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
