package com.skillsoft.concurrency;

import java.util.concurrent.TimeUnit;

public class FirstTask implements Runnable {

    ResourceOne rOne;
    ResourceTwo rTwo;

    public FirstTask(ResourceOne rOne, ResourceTwo rTwo) {
        this.rOne = rOne;
        this.rTwo = rTwo;
    }

    @Override
    public void run() {
        try {

            long writeLockOneStamp = rOne.rOneLock.writeLock();
            System.out.println("Lock acquired on ResourceOne by " + Thread.currentThread().getName() + ". Lock stamp is " + writeLockOneStamp);
            int updatedValue = rOne.myVar++;
            Thread.sleep(20000);
            rOne.myVar = updatedValue;
            rOne.rOneLock.unlock(writeLockOneStamp);
            System.out.println("Lock on ResourceOne released by " + Thread.currentThread().getName());

            long writeLockTwoStamp = rTwo.rTwoLock.writeLock();
            System.out.println("Lock acquired on ResourceTwo by " + Thread.currentThread().getName() + ". Lock stamp is " + writeLockTwoStamp);
            Thread.sleep(2000);
            rTwo.myVar--;
            rTwo.rTwoLock.unlock(writeLockTwoStamp);
            System.out.println("Lock on ResourceTwo released by " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
