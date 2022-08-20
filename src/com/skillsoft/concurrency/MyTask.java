package com.skillsoft.concurrency;

import java.util.concurrent.Semaphore;
import java.util.Random;

public class MyTask implements Runnable {

    SharedResource sr;
    Semaphore sem;
    String threadName;
    int numPermits = 1;

    public MyTask(SharedResource sr, Semaphore sem, int permits) {
        this.sr = sr;
        this.sem = sem;
        this.numPermits = permits;
    }

    @Override
    public void run() {
        try {
            threadName = Thread.currentThread().getName();
            System.out.println(threadName + " is waiting for " + numPermits + " the semaphore...");
            sem.acquire(numPermits);
            System.out.println(threadName + " has ACQUIRED " + numPermits + " permits.");

            // Do work
            Thread.sleep((long) (Math.random() * 1000) * 5);

            sem.release(numPermits);
            System.out.println(threadName + " has RELEASED " + numPermits + " permits!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SharedResource sharedRes = new SharedResource();
        int maxPermits = 4;
        Semaphore sem = new Semaphore(maxPermits);

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int permits = random.nextInt(maxPermits) + 1;
            Thread t = new Thread(new MyTask(sharedRes, sem, permits), "Task-" + i);
            t.start();
        }
    }
}
