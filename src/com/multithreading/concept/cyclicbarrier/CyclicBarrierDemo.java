package com.multithreading.concept.cyclicbarrier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    private static CyclicBarrier cyclicBarrier;
    private List<List<Integer>> partialResults = Collections.synchronizedList(new ArrayList<>());
    private static int NUM_PARTIAL_RESULTS;
    private static int NUM_WORKERS;
    private Random random = new Random();

    class NumberCruncherThread implements Runnable {

        @Override
        public void run() {
            List<Integer> partialResult = new ArrayList<>();
            String threadName = Thread.currentThread().getName();
            for (int i = 0; i < NUM_PARTIAL_RESULTS; i++) {
                System.out.print("Currently crunching the data with thread : " + threadName);
                partialResult.add(random.nextInt(10));

            }
            partialResults.add(partialResult);
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    class AggregaterThread implements Runnable {

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            int sum = partialResults.stream().flatMap(i -> i.stream()).reduce(0, Integer::sum);
            System.out.println("The current thread is : " + threadName);
            System.out.println("Final sum : " + sum);
        }
    }

    private void runSimulation(int numWorkers, int numOfPartialResults) {
        NUM_PARTIAL_RESULTS = numOfPartialResults;
        NUM_WORKERS = numWorkers;
        cyclicBarrier = new CyclicBarrier(NUM_WORKERS, new AggregaterThread());

        System.out.println("Spawning " + NUM_WORKERS + " worker threads to compute " + NUM_PARTIAL_RESULTS + " partial results each");

        for (int i = 0; i < NUM_WORKERS; i++) {
            Thread worker = new Thread(new NumberCruncherThread());
            worker.setName("Thread " + i);
            worker.start();
        }

    }

    public static void main(String args[]) {
        CyclicBarrierDemo cyclicBarrierDemo = new CyclicBarrierDemo();
        cyclicBarrierDemo.runSimulation(6, 2);
    }


}
