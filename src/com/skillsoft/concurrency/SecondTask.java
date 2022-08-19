package com.skillsoft.concurrency;

public class SecondTask implements Runnable{

    ResourceOne rOne;
    ResourceTwo rTwo;

    public SecondTask(ResourceOne rOne, ResourceTwo rTwo) {
        this.rOne = rOne;
        this.rTwo = rTwo;
    }

    @Override
    public void run() {
        try {

            synchronized (rTwo){
                System.out.println("Lock acquired on ResourceTwo by " + Thread.currentThread().getName());
                rTwo.myVar--;
                Thread.sleep(1000);
            }
            System.out.println("Lock on ResourceTwo released by "+ Thread.currentThread().getName());
            synchronized (rOne) {
                System.out.println("Lock acquired on ResourceOne by " + Thread.currentThread().getName());
                rOne.myVar--;
                Thread.sleep(1000);
            }
            System.out.println("Lock on ResourceOne released by "+ Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
