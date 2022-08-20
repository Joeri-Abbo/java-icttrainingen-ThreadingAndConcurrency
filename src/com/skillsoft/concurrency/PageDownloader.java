package com.skillsoft.concurrency;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CountDownLatch;

public class PageDownloader implements Runnable {
    String[] urlsList;
    CountDownLatch latch;

    public PageDownloader(String[] urlsList, CountDownLatch latch) {
        this.urlsList = urlsList;
        this.latch = latch;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(threadName + " has been interrupted before downloading " + urlsList[0]);
        }

        try {
            for (String urlString : urlsList) {
                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedException(threadName + " is interrupted");
                }
                URL url = new URL(urlString);
                String fileName = "files/" + urlString.substring(urlString.lastIndexOf('/') + 1).trim() + ".html";
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                }
                System.out.println(threadName + " has downloaded " + fileName);
                writer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        latch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        String[] urlsList = {"https://tracefy.com/nl", "https://tracefy.com/nl/b2b", "https://tracefy.com/nl/b2b/fleetmanagement", "https://tracefy.com/nl/b2b/deelvoertuigen-en-verhuur", "https://tracefy.com/nl/b2b/delivery", "https://tracefy.com/nl/b2b/fietsmerken-oem", "https://tracefy.com/nl/consumer", "https://tracefy.com/nl/diefstal-opsporing", "https://tracefy.com/nl/consumer/dealer-worden", "https://tracefy.com/nl/consumer/dealer-zoeken", "https://tracefy.com/nl/elektrische-fiets-verzekeren-met-tracefy", "https://tracefy.com/nl/over-tracefy", "https://tracefy.com/nl/over-tracefy/tracefy-team", "https://tracefy.com/nl/over-tracefy/werken-bij", "https://tracefy.com/nl/over-tracefy/media", "https://tracefy.com/nl/contact"};

        int maxThreads = 4;
        CountDownLatch latch = new CountDownLatch(maxThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(maxThreads);
        long startTime = System.currentTimeMillis();

        for (String url : urlsList) {
            Thread downloader = new Thread(new PageDownloader(new String[]{url}, latch));
            executorService.submit(downloader);
        }

        latch.await();
        executorService.shutdownNow();
        while (!executorService.isTerminated()) {
            Thread.sleep(1000);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Total time taken: " + (endTime - startTime) / 1000 + " s");
    }
}
