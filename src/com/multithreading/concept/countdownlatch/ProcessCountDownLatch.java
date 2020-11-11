package com.multithreading.concept.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * This class is to get understanding of CountDownLatch and its features.
 */
public class ProcessCountDownLatch {

    public static void main(String args[]) {
        System.out.println("Welcome to Interview preparation journey");

        CountDownLatch latch = new CountDownLatch(2);
        PrepareDataStructureTask dataStructureTask = new PrepareDataStructureTask(latch);
        Thread dataStructureThread = new Thread(dataStructureTask);

        PrepareDesignTask designTask = new PrepareDesignTask(latch);
        Thread desginThread = new Thread(designTask);
        dataStructureThread.start();
        desginThread.start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            System.out.println("oops you got an error, you need some more preparation, don't worry" +
                    "you will rock this but first resolve this error");
            e.printStackTrace();
        }
        System.out.println("Congratulations you are ready to rock this....");
    }


}