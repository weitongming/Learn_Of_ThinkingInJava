package com.weitongming.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by tim.wei on 2017/9/3.
 */
public class WaitingTask implements Runnable {
    private static int count = 0;
    private final int id = count ++ ;
    private CountDownLatch latch ;
    public WaitingTask(CountDownLatch latch){
        this.latch = latch ;
    }
    public void run() {
        try {
                latch.await();
                System.out.println("WaitingTask 正在开始");
        } catch (InterruptedException e) {
                e.printStackTrace();
        }
     }

    @Override
    public String toString() {
        return "WaitingTask:" + id ;
    }
}

