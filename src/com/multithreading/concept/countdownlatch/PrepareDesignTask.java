package com.multithreading.concept.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class PrepareDesignTask implements Runnable {
    private final CountDownLatch latch;

    public PrepareDesignTask(CountDownLatch latch){
        this.latch=latch;
    }
    @Override
    public void run() {
        try {
            System.out.println("Prepare Design pattern");
            Thread.currentThread().sleep(2000);
            System.out.println("Prepare SOLID");
            Thread.currentThread().sleep(2000);
            System.out.println("Prepare clean code");
            Thread.currentThread().sleep(2000);
            System.out.println("Prepare LLD");
            Thread.currentThread().sleep(5000);
            System.out.println("Prepare HLD");
            latch.countDown();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
