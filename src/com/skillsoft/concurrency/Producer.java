package com.skillsoft.concurrency;

public class Producer implements Runnable {
    SharedQueue sharedQueue;

    public Producer(SharedQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    String[] items = {"ItemOne", "ItemTwo", "ItemThree", "ItemFour", "ItemFive", "ItemSix", "ItemSeven", "ItemEight", "ItemNine", "ItemTen"};


    public void produce(String item) throws InterruptedException {

        sharedQueue.queueLock.lock();
        if (sharedQueue.queue.size() >= sharedQueue.capacity) {
            System.out.println("Queue is full. Producer is waiting");
            sharedQueue.notFull.await();
        }

        sharedQueue.queue.add(item);
        System.out.println("Produced : " + item);
        sharedQueue.notEmpty.signal();
        sharedQueue.queueLock.unlock();
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
