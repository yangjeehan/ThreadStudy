package com.yjh.study.nine;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 *  semaphore maintains a set of permits
 *  acquire() -> if a permit is available than takes it
 *  release() -> adds a permit
 *
 *  Semaphore just keeps a count of the number available
 *  new Semaphore(int permit, boolean fair)!!!
 */

enum Downloader {
    INSTANCE;

    private Semaphore semaphore = new Semaphore(5,true);

    public void downloadData() {

        try {
            semaphore.acquire();
            download();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }


    }

    private void download() {
        System.out.println("Downloading data from the web...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

public class App {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i=0 ; i<12;i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Downloader.INSTANCE.downloadData();
                }
            });
        }

    }

}
