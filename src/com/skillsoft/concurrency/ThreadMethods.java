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

                String threadGroup = Thread.currentThread().getThreadGroup().getName();
                int activeThread = Thread.activeCount();
                System.out.println("I am walking..." + " My group " + threadGroup + " has a activeCount of " + activeThread);
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
                String threadGroup = Thread.currentThread().getThreadGroup().getName();
                int activeThread = Thread.activeCount();
                System.out.println("I am chewing gum..." + " My group " + threadGroup + " has a activeCount of " + activeThread);
            }
        }
    }

    public static void main(String[] args) {

        ThreadGroup groupOne = new ThreadGroup("GroupOne");
        ThreadGroup groupTwo = new ThreadGroup("GroupTwo");

        Thread walkThreadOne = new Thread(groupOne, new Walk());
        Thread walkThreadTwo = new Thread(groupTwo, new Walk());
        Thread walkThreadThree = new Thread(groupTwo, new Walk());

        Thread chewThreadOne = new Thread(groupOne, new ChewGum());
        Thread chewThreadTwo = new Thread(groupTwo, new ChewGum());

        walkThreadOne.start();
        walkThreadTwo.start();
        walkThreadThree.start();

        chewThreadOne.start();
        chewThreadTwo.start();

        System.out.println("#Active threads for main: " + Thread.activeCount());
        System.out.println("#Active threads for GroupOne" + groupOne.activeCount());
        System.out.println("#Active threads for GroupTwo" + groupTwo.activeCount());
    }
}
