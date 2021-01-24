package com.multithreading.concept.threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
    public static void main(String args[]){
        Executor executor= Executors.newFixedThreadPool(3);

        for(int i=0;i<5;i++) {
            ((ExecutorService) executor).submit(new Task());
        }
    }


}
class Task implements Runnable{

    public void run(){
        System.out.println("task is running by thread : "+ Thread.currentThread().getName());
    }
}
