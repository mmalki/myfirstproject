package com.software2ms.myfirstproject;

import java.util.Arrays;
import java.util.Random;

public class ChrononDemo {
    public static final int SIZE = 1000;
    public static final Random GENERATOR = new Random();

    public static void main(String[] args) throws InterruptedException {
        final int[] array = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            array[i] = GENERATOR.nextInt();
        }
        final Thread quickSortThread = new Thread(new Runnable() {
            @Override
            public void run() {
                QuickSort.sort(Arrays.copyOf(array, array.length));
            }
        }, "Quick sort");

        final Thread bubbleThread = new Thread(new MyRunnable(array), "Bubble sort");

        quickSortThread.start();
        bubbleThread.start();

        quickSortThread.join();
        bubbleThread.join();
    }

    private static class MyRunnable implements Runnable {
        private final int[] array;

        public MyRunnable(int[] array) {
            this.array = array;
        }

        @Override
        public void run() {
            BubbleSort.sort(Arrays.copyOf(array, array.length));
        }
    }
}
