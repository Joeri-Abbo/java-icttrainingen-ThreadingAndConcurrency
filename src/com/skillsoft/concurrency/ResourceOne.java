package com.skillsoft.concurrency;

import java.util.concurrent.locks.StampedLock;

public class ResourceOne {
    public int myVar = 100;
    StampedLock rOneLock = new StampedLock();
}
