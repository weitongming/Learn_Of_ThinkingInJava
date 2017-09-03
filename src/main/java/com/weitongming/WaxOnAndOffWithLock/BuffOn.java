package com.weitongming.WaxOnAndOffWithLock;

import java.util.concurrent.TimeUnit;

/**
 * Created by weitongming on 2017/9/3.
 */
public class BuffOn implements  Runnable {
    private Car car ;
    public BuffOn(Car car){
        this.car = car ;
    }
    public void run()  {
        while (!Thread.interrupted()){
            try {
                car.waitingForWax();
                System.out.println("    正在抛光");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
