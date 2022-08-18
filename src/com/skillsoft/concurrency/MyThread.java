package com.skillsoft.concurrency;

public class MyThread implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i < 5; i++) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("i + " + i);
        }
    }

    public static void main(String[] args) {
        Thread myThread = new Thread(new MyThread());
        myThread.start();

    }
}
