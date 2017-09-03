package com.weitongming.WaxOnAndOffWithLock;

import java.util.concurrent.TimeUnit;

/**
 * Created by weitongming on 2017/9/3.
 */
public class Waxon implements Runnable {
    private Car  car ;

    public Waxon(Car car){
        this.car = car ;
    }

    public void run() {
        while(!Thread.interrupted()){
            try {
                System.out.println("正在打蜡");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitingForBuff();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
