package com.multithreading.concept.countdownlatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class WaitingWorker implements Runnable {
    private List<String> outputScrapper;
    private CountDownLatch readyThreadCounter;
    private CountDownLatch callingThreadCounter;
    private CountDownLatch completedThreadCounter;

    public WaitingWorker(
            List<String> outputScrapper,
            CountDownLatch readyThreadCounter,
            CountDownLatch callingThreadCounter,
            CountDownLatch completedThreadCounter) {
        this.outputScrapper = outputScrapper;
        this.readyThreadCounter = readyThreadCounter;
        this.callingThreadCounter = callingThreadCounter;
        this.completedThreadCounter = completedThreadCounter;
    }

    @Override
    public void run() {
            readyThreadCounter.countDown();
        try {
            callingThreadCounter.await();

            outputScrapper.add("Counted down");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            completedThreadCounter.countDown();
        }

    }
}
