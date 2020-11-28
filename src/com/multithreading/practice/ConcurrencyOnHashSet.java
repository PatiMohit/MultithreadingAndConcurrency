package com.multithreading.practice;

import java.util.HashSet;

public class ConcurrencyOnHashSet {

    private static HashSet<String> hashSet = new HashSet<>();


    public static void main(String args[]) throws InterruptedException {

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r1);
        t1.start();
        t2.start();
        Thread.sleep(15000);

        hashSet.forEach(System.out::println);

    }

    private static Runnable r1 = ()->{
            for (int i = 0; i < 10; i++) {
                hashSet.add("Thread : "+Thread.currentThread().getName()+ " i : " +i);
    }};

}
