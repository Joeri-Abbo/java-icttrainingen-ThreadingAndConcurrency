package com.skillsoft.concurrency;

import java.util.concurrent.TimeUnit;

public class SecondTask implements Runnable {

    ResourceOne rOne;
    ResourceTwo rTwo;

    public SecondTask(ResourceOne rOne, ResourceTwo rTwo) {
        this.rOne = rOne;
        this.rTwo = rTwo;
    }

    @Override
    public void run() {
        try {
            long writeLockTwoStamp = rTwo.rTwoLock.readLock();
            System.out.println("Lock acquired on ResourceTwo by " + Thread.currentThread().getName() + ". Lock stamp is " + writeLockTwoStamp);
            Thread.sleep(1000);
            rTwo.myVar++;
            rTwo.rTwoLock.unlock(writeLockTwoStamp);
            System.out.println("Lock on ResourceTwo released by " + Thread.currentThread().getName());

            long writeLockOneStamp = rOne.rOneLock.readLock();
            System.out.println("Lock acquired on ResourceOne by " + Thread.currentThread().getName() + ". Lock stamp is " + writeLockOneStamp);
            Thread.sleep(1000);
            rOne.myVar--;
            rOne.rOneLock.unlock(writeLockOneStamp);
            System.out.println("Lock on ResourceOne released by " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
