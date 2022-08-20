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

public class RecursiveDownloader extends RecursiveAction {
    String[] urlsList;

    private static final int THRESHOLD = 3;

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
}
