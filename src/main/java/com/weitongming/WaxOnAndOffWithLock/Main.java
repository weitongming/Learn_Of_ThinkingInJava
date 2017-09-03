package com.weitongming.WaxOnAndOffWithLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by weitongming on 2017/9/3.
 */
public class Main {
    public static void main(String args[]) throws InterruptedException {
        Car car = new Car() ;
        ExecutorService executorService = Executors.newCachedThreadPool();
        Waxon waxon = new Waxon(car) ;
        BuffOn buffOn = new BuffOn(car) ;
        executorService.execute(waxon);
        executorService.execute(buffOn);
        TimeUnit.SECONDS.sleep(5);
        executorService.shutdownNow();
    }
}
