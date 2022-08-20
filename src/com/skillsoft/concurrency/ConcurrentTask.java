package com.skillsoft.concurrency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ConcurrentTask implements Runnable {
    private static final int NUM_ITERATIONS = 10000;

    public Collection<String> commonResource;

    public ConcurrentTask(Collection<String> commonResource) {
        this.commonResource = commonResource;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        try {
            for (int i = 0; i < NUM_ITERATIONS; i++) {
                commonResource.add(threadName + "-data-" + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(threadName + " has finished execution");
    }

    public static void main(String[] args) throws InterruptedException {
        Collection<String> commonRes = Collections.synchronizedCollection(new ArrayList<>());

        Thread firstThread = new Thread(new ConcurrentTask(commonRes), "FirstThread");
        Thread secondThread = new Thread(new ConcurrentTask(commonRes), "SecondThread");

        firstThread.start();
        secondThread.start();

        firstThread.join();
        secondThread.join();

        System.out.println("#elements in commonRes: " + commonRes.size());
    }
}
