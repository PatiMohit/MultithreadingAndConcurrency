package com.multithreading.practice;

import java.util.Scanner;

public class PrintEvenOddUsingTwoThreads {
    private volatile static int counter=1;
    private static int lastNumber;
    private void printOddNumber() {
        synchronized (this) {
            while (counter < lastNumber) {
                while (counter % 2 != 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(counter);
                counter++;
                notify();
            }
        }
    }

    private void printEvenNumber() {
        synchronized (this) {
            while (counter < lastNumber) {
                while (counter % 2 == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(counter);
                counter++;
                notify();
            }
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number:--");
        lastNumber = sc.nextInt();
        PrintEvenOddUsingTwoThreads printEvenOddUsingTwoThreads = new PrintEvenOddUsingTwoThreads();
        Thread oddThread = new Thread(() -> {
            printEvenOddUsingTwoThreads.printOddNumber();
        });
        Thread evenThread = new Thread(() -> {
            printEvenOddUsingTwoThreads.printEvenNumber();
        });
        oddThread.start();
        evenThread.start();
    }


}
