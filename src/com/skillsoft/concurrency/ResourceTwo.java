package com.skillsoft.concurrency;

import java.util.concurrent.locks.StampedLock;

public class ResourceTwo {

    public int myVar = 1000;
    StampedLock rTwoLock = new StampedLock();

}
