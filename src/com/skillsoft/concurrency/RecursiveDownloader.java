package com.skillsoft.concurrency;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
@SuppressWarnings("serial")
public class RecursiveDownloader extends RecursiveAction {
    String[] urlsList;

    private static final int THRESHOLD = 6;

    public RecursiveDownloader(String[] urlsList) {
        this.urlsList = urlsList;
    }

    @Override
    protected void compute() {
        if (urlsList.length > THRESHOLD) {
            ForkJoinTask.invokeAll(createSubTasks());
        } else {
            download(urlsList);
        }
    }

    private List<RecursiveDownloader> createSubTasks() {
        List<RecursiveDownloader> subtasks = new ArrayList<>();
        String[] firstSet = Arrays.copyOfRange(urlsList, 0, urlsList.length / 2);
        String[] secondSet = Arrays.copyOfRange(urlsList, urlsList.length / 2, urlsList.length);

        subtasks.add(new RecursiveDownloader(firstSet));
        subtasks.add(new RecursiveDownloader(secondSet));
        return subtasks;
    }

    public void download(String[] urlsList) {
        String threadName = Thread.currentThread().getName();

        System.out.println(threadName + " has STARTED!");
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
                System.out.println(threadName + " has downloaded " + fileName);
                writer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(threadName + " has FINISHED!");
    }

    public static void main(String[] args) throws InterruptedException {
        String[] urlsList = {"https://tracefy.com/nl", "https://tracefy.com/nl/b2b", "https://tracefy.com/nl/b2b/fleetmanagement", "https://tracefy.com/nl/b2b/deelvoertuigen-en-verhuur", "https://tracefy.com/nl/b2b/delivery", "https://tracefy.com/nl/b2b/fietsmerken-oem", "https://tracefy.com/nl/consumer", "https://tracefy.com/nl/diefstal-opsporing", "https://tracefy.com/nl/consumer/dealer-worden", "https://tracefy.com/nl/consumer/dealer-zoeken", "https://tracefy.com/nl/elektrische-fiets-verzekeren-met-tracefy", "https://tracefy.com/nl/over-tracefy", "https://tracefy.com/nl/over-tracefy/tracefy-team", "https://tracefy.com/nl/over-tracefy/werken-bij", "https://tracefy.com/nl/over-tracefy/media", "https://tracefy.com/nl/contact"};

        RecursiveDownloader task = new RecursiveDownloader(urlsList);

        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(task);
    }
}
