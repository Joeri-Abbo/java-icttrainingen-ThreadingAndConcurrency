package com.skillsoft.concurrency;

import java.util.concurrent.ArrayBlockingQueue;

public class Producer implements Runnable {
    ArrayBlockingQueue<String> sharedQueue;

    public Producer(ArrayBlockingQueue<String> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    String[] items = {"ItemOne", "ItemTwo", "ItemThree", "ItemFour", "ItemFive", "ItemSix", "ItemSeven", "ItemEight", "ItemNine", "ItemTen"};


    public void produce(String item) throws InterruptedException {

        String threadName = Thread.currentThread().getName();
        sharedQueue.put(item);
        System.out.println(threadName + " produced : " + item);
    }

    @Override
    public void run() {
        for (int i = 0; i < items.length; i++) {
            try {
                Thread.sleep((long) (Math.random() * 1000) * 5);
                produce(items[i]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
