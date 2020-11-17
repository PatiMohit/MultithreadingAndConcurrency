package com.multithreading.concept.countdownlatch;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;


public class CountdownLatchExampleIntegrationTest {

    @Test
    public void mainThreadBlockOut() throws InterruptedException {
        //Given
        List<String> outputScrapper = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch latch = new CountDownLatch(5);

        List<Thread> workers = Stream.generate(() -> new Thread(new Worker(outputScrapper, latch))).limit(5).collect(toList());
        workers.forEach(Thread::start);
        latch.await();
        outputScrapper.add("Latch released");
        assertThat(outputScrapper
        ).containsExactly("Counted down", "Counted down",
                "Counted down", "Counted down", "Counted down", "Latch released");
    }

    @Test
    public void failingToParallelProcess() throws InterruptedException {
        List<String> outputScrapper = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch latch = new CountDownLatch(5);

        List<Thread> workers = Stream.generate(() -> new Thread(new Worker(outputScrapper, latch))).limit(5).collect(toList());
        workers.forEach(Thread::start);
        final boolean result = latch.await(3L, TimeUnit.SECONDS);
        assertThat(result).isFalse();
    }

    @Test
    public void multipleLatchProcessTest() throws InterruptedException {
        List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch readyThreadCounter = new CountDownLatch(5);
        CountDownLatch callingThreadBlocker = new CountDownLatch(1);
        CountDownLatch completedThreadCounter = new CountDownLatch(5);
        List<Thread> workers = Stream.generate(() -> new Thread(new WaitingWorker(outputScraper, readyThreadCounter, callingThreadBlocker, completedThreadCounter))).limit(5).collect(toList());

        // When
        workers.forEach(Thread::start);
        readyThreadCounter.await(); // Block until workers start
        outputScraper.add("Workers ready");
        callingThreadBlocker.countDown(); // Start workers
        completedThreadCounter.await(); // Block until workers finish
        outputScraper.add("Workers complete");

        // Then
        assertThat(outputScraper).containsExactly("Workers ready", "Counted down",
                "Counted down", "Counted down", "Counted down", "Counted down", "Workers complete");
    }
}
