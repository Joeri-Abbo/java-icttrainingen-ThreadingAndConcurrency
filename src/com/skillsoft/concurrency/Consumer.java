package com.skillsoft.concurrency;
import java.util.concurrent.ArrayBlockingQueue;

public class Consumer implements Runnable {
    ArrayBlockingQueue<String> sharedQueue;
    String consumerName;
    int consumerCapacity;

    public Consumer(ArrayBlockingQueue<String> sharedQueue, String name, int capacity) {
        this.sharedQueue = sharedQueue;
        this.consumerName = name;
        this.consumerCapacity = capacity;
    }

    public void consume() throws InterruptedException {
        String item = sharedQueue.take();
        System.out.println(consumerName + " has consumed " + item);

    }

    @Override
    public void run() {
        for (int i = 0; i < consumerCapacity; i++) {
            try {
                Thread.sleep((long) (Math.random() * 1000) * 5);
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(consumerName + " has run its course");
    }
}
