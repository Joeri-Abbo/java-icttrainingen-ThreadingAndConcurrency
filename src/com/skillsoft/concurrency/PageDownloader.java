package com.skillsoft.concurrency;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
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

        int maxThreads = 2;
        CountDownLatch latch = new CountDownLatch(maxThreads);
        Thread downloaderOne = new Thread(new PageDownloader(Arrays.copyOfRange(urlsList, 0, urlsList.length / 2), latch));
        Thread downloaderTwo = new Thread(new PageDownloader(Arrays.copyOfRange(urlsList, urlsList.length / 2, urlsList.length), latch));

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        long startTime = System.currentTimeMillis();

        executorService.submit(downloaderOne);
        executorService.submit(downloaderTwo);

        latch.await();
        long endTime = System.currentTimeMillis();

        System.out.println("Total time taken: " + (endTime - startTime) / 1000 + " s");
    }
}
