package com.skillsoft.concurrency;

import java.util.LinkedList;

public class ProducerConsumer {
    public static void main(String[] args) {
        SharedQueue sharedQueue = new SharedQueue(new LinkedList<String>(), 2);

        Producer producerOne = new Producer(sharedQueue);
        Producer producerTwo = new Producer(sharedQueue);

        Consumer consumerOne = new Consumer(sharedQueue, "ConsumerOne", 7);
        Consumer consumerTwo = new Consumer(sharedQueue, "ConsumerTwo", 8);
        Consumer consumerThree = new Consumer(sharedQueue, "ConsumerThree", 5);

        Thread pOne = new Thread(producerOne, "ProducerOne Thread");
        Thread pTwo = new Thread(producerTwo, "ProducerTwo Thread");

        Thread cOne = new Thread(consumerOne, "ConsumerOne Thread");
        Thread cTwo = new Thread(consumerTwo, "ConsumerTwo Thread");
        Thread cThree = new Thread(consumerThree, "ConsumerThree Thread");

        pOne.start();
        pTwo.start();

        cOne.start();
        cTwo.start();
        cThree.start();
    }
}
