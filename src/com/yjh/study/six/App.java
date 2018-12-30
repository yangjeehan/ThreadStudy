package com.yjh.study.six;

import java.util.ArrayList;
import java.util.List;

class Processor {

    private List<Integer> list = new ArrayList<>();
    private final int LIMIT = 5;
    private final int BOTTOM = 0;
    private final Object lock = new Object();
    private int value = 0;

    public void produce() throws InterruptedException {
        synchronized (lock) {

            while (true){
                if(list.size() == LIMIT) {
                    System.out.println("Wating for removing items from the list..");
                    lock.wait();
                }else {
                    System.out.println("adding : " + value);
                    list.add(value);
                    value++;
                    lock.notify();
                }

                Thread.sleep(500);
            }

        }
    }

    public void consume() throws InterruptedException {

        synchronized (lock) {
            while (true) {

                if(list.size() == BOTTOM) {
                    System.out.println("Wating for adding items from the list");
                    lock.wait();
                }else {
                    System.out.println("Removing : " + list.remove(--value));
                    lock.notify();
                }
                Thread.sleep(500);
            }

        }
    }

}


public class App {
    static Processor processor = new Processor();

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    processor.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    processor.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        t1.start();
        t2.start();


    }

}
