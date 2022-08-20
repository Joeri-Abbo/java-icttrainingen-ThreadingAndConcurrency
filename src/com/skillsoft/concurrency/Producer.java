package com.skillsoft.concurrency;

public class Producer implements Runnable {
    SharedQueue sharedQueue;

    public Producer(SharedQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    String[] items = {"ItemOne", "ItemTwo", "ItemThree", "ItemFour", "ItemFive", "ItemSix", "ItemSeven", "ItemEight", "ItemNine", "ItemTen"};


    public void produce(String item) throws InterruptedException {
        synchronized (sharedQueue) {
            if (sharedQueue.queue.size() >= sharedQueue.capacity) {
                System.out.println("Queue is full. Producer is waiting");
                sharedQueue.wait();
                System.out.println("Producer has woken up");
            }
        }

        synchronized (sharedQueue) {
            sharedQueue.queue.add(item);
            System.out.println("Produced : " + item);
            sharedQueue.notify();
        }
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
