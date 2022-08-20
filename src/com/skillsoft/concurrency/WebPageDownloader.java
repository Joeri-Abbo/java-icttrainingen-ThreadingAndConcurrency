package com.skillsoft.concurrency;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class WebPageDownloader implements Runnable {
    String[] urlsList;

    public WebPageDownloader(String[] urlsList) {
        this.urlsList = urlsList;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        System.out.println(threadName + " has STARTED a run!");
        try {
            for (String urlString : urlsList) {
                URL url = new URL(urlString);
                String fileName = "files/" + urlString.substring(urlString.lastIndexOf('/') + 1).trim() + ".html";
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                }
                System.out.println(" Download complete for " + fileName);
                writer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(threadName + " has COMPLETED a run!");
    }

    public static void main(String[] args) throws InterruptedException {
        String[] urlsList = {"https://tracefy.com/nl", "https://tracefy.com/nl/b2b", "https://tracefy.com/nl/b2b/fleetmanagement", "https://tracefy.com/nl/b2b/deelvoertuigen-en-verhuur", "https://tracefy.com/nl/b2b/delivery", "https://tracefy.com/nl/b2b/fietsmerken-oem", "https://tracefy.com/nl/consumer", "https://tracefy.com/nl/diefstal-opsporing", "https://tracefy.com/nl/consumer/dealer-worden", "https://tracefy.com/nl/consumer/dealer-zoeken", "https://tracefy.com/nl/elektrische-fiets-verzekeren-met-tracefy", "https://tracefy.com/nl/over-tracefy", "https://tracefy.com/nl/over-tracefy/tracefy-team", "https://tracefy.com/nl/over-tracefy/werken-bij", "https://tracefy.com/nl/over-tracefy/media", "https://tracefy.com/nl/contact"};

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

        Runnable downloaderOne = new WebPageDownloader(Arrays.copyOfRange(urlsList, 0, urlsList.length / 2));
        Runnable downloaderTwo = new WebPageDownloader(Arrays.copyOfRange(urlsList, urlsList.length / 2, urlsList.length));

        ScheduledFuture fOne = executorService.scheduleAtFixedRate(downloaderOne,10,60, TimeUnit.SECONDS);
        ScheduledFuture fTwo = executorService.scheduleAtFixedRate(downloaderTwo, 15,60, TimeUnit.SECONDS);

        System.out.println("The jobs have been scheduled");
        long startTime = System.currentTimeMillis();

        try {
            System.out.println("Download time for first half: " + fOne.get());
            System.out.println("Download time for second half: " + fTwo.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Elapsed time since scheduling: " + (endTime - startTime) / 1000 + " s");

        executorService.shutdown();
    }
}
