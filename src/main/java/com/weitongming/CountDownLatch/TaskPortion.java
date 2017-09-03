package com.weitongming.CountDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by tim.wei on 2017/9/3.
 */
public class TaskPortion implements  Runnable {
    private static int count = 0;
    private final int id = count++;
    private  static Random random = new Random(47) ;
    private final CountDownLatch latch ;

    public TaskPortion(CountDownLatch latch) {
        this.latch = latch;
        latch.countDown();
    }

    public void run() {
        try {
            doWork();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(random.nextInt(2000));
        System.out.println(this + "completed");
    }

    @Override
    public String toString() {
        return "TackPortion " + id + "completed";
    }
}
