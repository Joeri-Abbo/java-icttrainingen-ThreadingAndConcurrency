package com.skillsoft.concurrency;

import java.util.concurrent.Semaphore;

public class MyTask implements Runnable {

    SharedResource sr;
    Semaphore sem;
    String threadName;

    public MyTask(SharedResource sr, Semaphore sem) {
        this.sr = sr;
        this.sem = sem;
    }

    @Override
    public void run() {
        try {
            threadName = Thread.currentThread().getName();
            System.out.println(threadName + " is waiting for the semaphore...");
            sem.acquire();
            System.out.println(threadName + " has ACQUIRED the semaphore! ");

            Thread.sleep((long) (Math.random() * 1000) * 5);
            int numListElements = sr.myList.size();

            sem.release();
            System.out.println(threadName + " has RELEASED the semaphore! ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SharedResource sharedRes = new SharedResource();
        Semaphore sem = new Semaphore(4);

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new MyTask(sharedRes, sem), "Thread " + i);
            t.start();
        }
    }
}
