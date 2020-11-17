package com.multithreading.concept.countdownlatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;


public class Worker implements Runnable {
    private final List<String> outputScrapper;
    private final CountDownLatch countDownLatch;

    public Worker(List<String> outputScrapper, CountDownLatch countDownLatch){
        this.outputScrapper=outputScrapper;
        this.countDownLatch=countDownLatch;
    }
    @Override
    public void run() {
        System.out.println("Do processing in worker's run method");
        outputScrapper.add("Counted down");
        countDownLatch.countDown();

    }
}
