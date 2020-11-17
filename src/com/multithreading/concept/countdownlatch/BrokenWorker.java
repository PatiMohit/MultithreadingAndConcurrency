package com.multithreading.concept.countdownlatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class BrokenWorker implements Runnable {

    private final List<String> outputScrapper;
    private final CountDownLatch countDownLatch;

    public BrokenWorker(List<String> outputScrapper, CountDownLatch countDownLatch) {
        this.outputScrapper = outputScrapper;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        if(true){
            throw new RuntimeException("Okay I am an error, got it ?");
        }
        countDownLatch.countDown();
        outputScrapper.add("Counted down");
    }
}
