package com.multithreading.concept.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class PrepareDataStructureTask implements Runnable {
    private final CountDownLatch latch;

    public PrepareDataStructureTask(CountDownLatch latch){
        this.latch=latch;
    }
    @Override
    public void run() {
        try {
            System.out.println("Prepare List");
            Thread.currentThread().sleep(2000);
            System.out.println("Prepare Set");
            Thread.currentThread().sleep(2000);
            System.out.println("Prepare Tree");
            Thread.currentThread().sleep(2000);
            System.out.println("Prepare Graph");
            Thread.currentThread().sleep(5000);
            System.out.println("Prepare Misc");
            latch.countDown();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
