package com.weitongming.ConsummerAndProductor;

import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * Created by weitongming on 2017/9/2.
 */
@Data
public class WaitPerson implements Runnable {
    private Restaurant boss;

    public void run() {
        try {
            while (!Thread.interrupted()){
                synchronized (this){
                    while (boss.getMeal() == null){
                        wait();
                    }
                }
                System.out.println("上菜啦：" + boss.getMeal().getOrderNUM());
                TimeUnit.MILLISECONDS.sleep(100);
                synchronized (boss.getChef()){
                    boss.setMeal(null);
                    boss.getChef().notifyAll();
                }
            }
        }
        catch (InterruptedException e) {
            System.out.println("wait person 中断");
        }
    }
    public WaitPerson(Restaurant restaurant){
        this.boss = restaurant;
    }
}
