package com.skillsoft.concurrency;

import java.util.Queue;

public class SharedQueue {
    Queue<String> queue;
    int capacity;

    public SharedQueue(Queue<String> queue, int capacity) {
        this.capacity = capacity;
        this.queue = queue;
    }
}
