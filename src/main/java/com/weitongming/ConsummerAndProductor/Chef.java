package com.weitongming.ConsummerAndProductor;

import java.util.concurrent.TimeUnit;

/**
 * Created by weitongming on 2017/9/2.
 */
public class Chef implements Runnable {
    private Restaurant  boss;
    private int count;

    public Chef(Restaurant restaurant){
        this.boss = restaurant;
        count = 0;
    }
    public void run() {
        while (!Thread.interrupted()){
            try {
                synchronized (this){
                    while (boss.getMeal() != null){
                        wait();
                    }
                }
                if (++count > 9){
                    System.out.println("没材料了，不干了");
                    boss.getExecutorService().shutdownNow();
                }
                System.out.println("正在做菜" + count);
                TimeUnit.MILLISECONDS.sleep(100);
                synchronized (boss.getWaitPerson()){
                    boss.setMeal(new Meal(count+""));
                    boss.getWaitPerson().notifyAll();
                }
            }catch (InterruptedException e) {
                System.out.println("chef 中断");
            }
        }

    }
}
