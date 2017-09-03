package com.weitongming.Semaphore;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * Created by tim.wei on 2017/9/4.
 */
public class CheckOutTask<T> implements Runnable {
    private static  int counter = 0;
    private  final int id = counter ++ ;
    private Pool<T> pool ;
    public CheckOutTask(Pool<T> pool){
        this.pool = pool;
    }
    @Override
    public void run() {
        try {
            T item = pool.checkout();
            System.out.println(this + " checkOut " + item);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(this + " checkIn " + item);
            pool.checkIn(item);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "CheckOutTask : " + id ;
    }
}
