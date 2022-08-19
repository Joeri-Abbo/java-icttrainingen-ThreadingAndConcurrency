package com.skillsoft.concurrency;

public class ThreadMethods {
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

        walkThread.setPriority(9);
        chewThread.setPriority(2);
        System.out.println();
        System.out.println("WalkThread's priority: " + walkThread.getPriority());
        System.out.println("ChewThread's priority: " + chewThread.getPriority());
        System.out.println("Main's priority: " + Thread.currentThread().getPriority());

        walkThread.start();
        chewThread.start();
        System.out.println();
        System.out.println();
    }
}
