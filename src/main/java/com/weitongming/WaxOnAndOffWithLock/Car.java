package com.weitongming.WaxOnAndOffWithLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by weitongming on 2017/9/3.
 */
public class Car {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition() ;
    private boolean waxon = false;

    public void waxed(){
        lock.lock();
        try {
            waxon = true ;
            System.out.println("打蜡完成");
            condition.signalAll();
        }finally {
            System.out.println("打蜡 unlock");
            lock.unlock();
        }
    }

    public void buffed(){
        lock.lock();
        try {
            waxon = false ;
            condition.signalAll();
            System.out.println("抛光完成");
        }finally {
            System.out.println(" 抛光 unlock");
            lock.unlock();
        }
    }

    public void waitingForWax() throws InterruptedException {
        lock.lock();
        try {
            while (waxon == false){
                System.out.println("    等待打蜡");
                condition.await();
            }
        }finally {
            lock.unlock();
        }
    }

    public void waitingForBuff() throws InterruptedException {
        lock.lock();
        try {
            while(waxon == true){
                System.out.println("    等待抛光");
                condition.await();
            }
        }finally {
            lock.unlock();
        }
    }
}
