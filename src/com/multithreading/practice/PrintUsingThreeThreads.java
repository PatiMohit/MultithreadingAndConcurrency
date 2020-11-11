package com.multithreading.practice;

import java.util.Scanner;
/*
* The logic to use k threads to print the number in sequence
* is to have the remainder starting from 0 to k-1 for all threads.
* This will work even if we change the order between start call
* for thread as other thread will print the number whichever gets
* executed first
*/
public class PrintUsingThreeThreads {
    private volatile static int counter=1;
    private static int lastNumber;
    private void printFromOne() {
        synchronized (this) {
            while (counter < lastNumber) {
                while (counter % 3 == 1) {
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

    private void printFromTwo() {
        synchronized (this) {
            while (counter < lastNumber) {
                while (counter % 3 == 2) {
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
    private void printFromThree() {
        synchronized (this) {
            while (counter < lastNumber) {
                while (counter % 3 == 0) {
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
        PrintUsingThreeThreads PrintUsingThreeThreads = new PrintUsingThreeThreads();
        Thread oneThread = new Thread(() -> {
            PrintUsingThreeThreads.printFromOne();
        });
        Thread twoThread = new Thread(() -> {
            PrintUsingThreeThreads.printFromTwo();
        });
        Thread threeThread= new Thread(() -> {
            PrintUsingThreeThreads.printFromThree();
        });
        oneThread.start();
        twoThread.start();
        threeThread.start();
    }


}
