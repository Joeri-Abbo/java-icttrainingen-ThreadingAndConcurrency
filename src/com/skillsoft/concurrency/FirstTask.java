package com.skillsoft.concurrency;

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

            boolean rOneLockAcquired = rOne.rOneLock.tryLock();
            if (rOneLockAcquired){
                System.out.println("Lock acquired on ResourceOne by " + Thread.currentThread().getName());
                rOne.myVar++;
                Thread.sleep(1000);
                rOne.rOneLock.unlock();
                System.out.println("Lock on ResourceOne released by " + Thread.currentThread().getName());
            }
            boolean rTwoLockAcquired = rTwo.rTwoLock.tryLock();
            if (rTwoLockAcquired){
                rTwo.rTwoLock.lock();
                System.out.println("Lock acquired on ResourceTwo by " + Thread.currentThread().getName());
                rTwo.myVar--;
                Thread.sleep(1000);
                rTwo.rTwoLock.unlock();
                System.out.println("Lock on ResourceTwo released by " + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
