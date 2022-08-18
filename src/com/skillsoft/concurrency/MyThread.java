package com.skillsoft.concurrency;

public class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.println("This has been executed by a thread");
    }

    public static void main(String[] args) {
        Thread myThread = new Thread(new MyThread());

        myThread.start();
        System.out.println("Is myThread a Runnable? " + (myThread instanceof Runnable));
        System.out.println("Is myThread a Thread? " + (myThread instanceof Thread));
        System.out.println("Is myThread a Object? " + (myThread instanceof Object));
    }
}
