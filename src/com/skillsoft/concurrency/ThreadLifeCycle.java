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
                System.out.println("I am walking... My state is " + Thread.currentThread().getState());
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

        try {
            walkThread.start();
            System.out.println("State of WalkThread thread after start: " + walkThread.getState());
            System.out.println("State of main thread after WalkThread start: " + Thread.currentThread().getState());
            Thread.sleep(1000);
            walkThread.join(5000);
            System.out.println("State of WalkThread thread after join: " + walkThread.getState());
            System.out.println("State of main thread after WalkThread join: " + Thread.currentThread().getState());

            System.out.println("Main thread will sleep for 20s...");
            Thread.sleep(20000);

            System.out.println("State of WalkThread thread at the end : " + walkThread.getState());
            System.out.println("State of main thread after WalkThread  at the end: " + Thread.currentThread().getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
