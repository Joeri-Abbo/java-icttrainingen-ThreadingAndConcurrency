package com.skillsoft.concurrency;

import java.util.LinkedList;

public class ProducerConsumer {
    public static void main(String[] args) {
        SharedQueue sharedQueue = new SharedQueue(new LinkedList<String>(), 2);

        Producer producer = new Producer(sharedQueue);
        Consumer consumerOne = new Consumer(sharedQueue, "ConsumerOne", 7);
        Consumer consumerTwo = new Consumer(sharedQueue, "ConsumerTwo", 3);

        Thread p = new Thread(producer, "Producer Thread");
        Thread cOne = new Thread(consumerOne, "ConsumerOne Thread");
        Thread cTwo = new Thread(consumerTwo, "ConsumerTwo Thread");

        p.start();
        cOne.start();
        cTwo.start();
    }
}
