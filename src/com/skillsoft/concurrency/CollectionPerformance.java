package com.skillsoft.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

public class CollectionPerformance {
    private ArrayList<String> myList = new ArrayList<String>();
    List<String> mySyncList = Collections.synchronizedList(new ArrayList<String>());
    private Vector<String> myVector = new Vector<String>();
    private CopyOnWriteArrayList<String> myCOWList = new CopyOnWriteArrayList<String>();
    private HashMap<String, String> myHashMap = new HashMap<String, String>();
    private Hashtable<String, String> myHashtable = new Hashtable<String, String>();
    private ConcurrentHashMap<String, String> myConcurrentHashMap = new ConcurrentHashMap<String, String>();

    private static final int NUM_ITERATIONS = 100000;

    public void testArrayList() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < NUM_ITERATIONS; i++) {
            myList.add("data-" + i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Total time ArrayList: " + (endTime - startTime) + "ms");
    }

    public void testSynchronizedList() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < NUM_ITERATIONS; i++) {
            mySyncList.add("data-" + i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Total time SynchronizedList: " + (endTime - startTime) + "ms");
    }

    public void testVector() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < NUM_ITERATIONS; i++) {
            myVector.add("data-" + i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Total time Vector: " + (endTime - startTime) + "ms");
    }

    public void testCOWArrayList() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < NUM_ITERATIONS; i++) {
            myCOWList.add("data-" + i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Total time COWArrayList: " + (endTime - startTime) + "ms");
    }

    public void testHashMap() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < NUM_ITERATIONS; i++) {
            myHashMap.put("key-" + i, "data-" + i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Total time HashMap: " + (endTime - startTime) + "ms");
    }

    public void testHashTable() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < NUM_ITERATIONS; i++) {
            myHashtable.put("key-" + i, "data-" + i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Total time HashTable: " + (endTime - startTime) + "ms");
    }

    public void testConcurrentHashMap() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < NUM_ITERATIONS; i++) {
            myConcurrentHashMap.put("key-" + i, "data-" + i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Total time ConcurrentHashMap: " + (endTime - startTime) + "ms");
    }


    public static void main(String[] args) {
        CollectionPerformance cp = new CollectionPerformance();

        cp.testArrayList();
        cp.testSynchronizedList();
        cp.testVector();
        cp.testCOWArrayList();

        cp.testHashMap();
        cp.testHashTable();
        cp.testConcurrentHashMap();
    }
}
