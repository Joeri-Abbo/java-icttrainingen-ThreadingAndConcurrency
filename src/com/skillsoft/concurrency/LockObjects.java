package com.skillsoft.concurrency;

public class LockObjects {
    public static void main(String[] args) throws InterruptedException {
        ResourceOne rOne = new ResourceOne();
        ResourceTwo rTwo = new ResourceTwo();

        Thread firstTaskThread = new Thread(new FirstTask(rOne, rTwo), "firstTaskThread");
        Thread secondTaskThread = new Thread(new SecondTask(rOne, rTwo), "secondTaskThread");

        System.out.println("Starting the two threads...");

        firstTaskThread.start();
        secondTaskThread.start();

        firstTaskThread.join();
        secondTaskThread.join();
        System.out.println("The two threads are done");
    }
}
