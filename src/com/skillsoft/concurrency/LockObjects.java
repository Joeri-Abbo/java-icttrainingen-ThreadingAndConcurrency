package com.skillsoft.concurrency;

public class LockObjects {
    public static void main(String[] args) throws InterruptedException {
        ResourceOne r1 = new ResourceOne();
        ResourceTwo r2 = new ResourceTwo();

        Thread firstTaskThread = new Thread(new FirstTask(r1, r2), "firstTaskThread");
        Thread secondTaskThread = new Thread(new SecondTask(r1, r2), "secondTaskThread");
        Thread anotherSecondTaskTread = new Thread(new SecondTask(r1, r2), "anotherSecondTaskTread");

        System.out.println("Starting the three threads...");

        firstTaskThread.start();
        secondTaskThread.start();
        anotherSecondTaskTread.start();

        firstTaskThread.join();
        secondTaskThread.join();
        anotherSecondTaskTread.join();
        System.out.println("The three threads are done");
        System.out.println("End value of resources: " + r1.myVar + ", " + r2.myVar);
    }
}
