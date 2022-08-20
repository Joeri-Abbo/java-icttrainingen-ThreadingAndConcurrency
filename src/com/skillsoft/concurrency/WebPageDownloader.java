package com.skillsoft.concurrency;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WebPageDownloader implements Callable<Long> {
    String[] urlsList;

    public WebPageDownloader(String[] urlsList) {
        this.urlsList = urlsList;
    }

    @Override
    public Long call() throws Exception {
        long startTime = System.currentTimeMillis();

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

        long endTime = System.currentTimeMillis();

        return endTime - startTime;
    }

    public static void main(String[] args) throws InterruptedException {
        String[] urlsList = {"https://tracefy.com/nl", "https://tracefy.com/nl/b2b", "https://tracefy.com/nl/b2b/fleetmanagement", "https://tracefy.com/nl/b2b/deelvoertuigen-en-verhuur", "https://tracefy.com/nl/b2b/delivery", "https://tracefy.com/nl/b2b/fietsmerken-oem", "https://tracefy.com/nl/consumer", "https://tracefy.com/nl/diefstal-opsporing", "https://tracefy.com/nl/consumer/dealer-worden", "https://tracefy.com/nl/consumer/dealer-zoeken", "https://tracefy.com/nl/elektrische-fiets-verzekeren-met-tracefy", "https://tracefy.com/nl/over-tracefy", "https://tracefy.com/nl/over-tracefy/tracefy-team", "https://tracefy.com/nl/over-tracefy/werken-bij", "https://tracefy.com/nl/over-tracefy/media", "https://tracefy.com/nl/contact"};

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Callable<Long> downloaderOne = new WebPageDownloader(Arrays.copyOfRange(urlsList, 0, urlsList.length / 2));
        Callable<Long> downloaderTwo = new WebPageDownloader(Arrays.copyOfRange(urlsList, urlsList.length / 2, urlsList.length));

        Future<Long> fOne = executorService.submit(downloaderOne);
        Future<Long> fTwo = executorService.submit(downloaderTwo);

        try {
            System.out.println("Download time for first half: " + fOne.get());
            System.out.println("Download time for second half: " + fTwo.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
