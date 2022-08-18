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
                System.out.println("I am walking...");
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
        Thread chewThread = new Thread(new ChewGum());
        try {
            walkThread.start();
            walkThread.join(5000);
            chewThread.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
