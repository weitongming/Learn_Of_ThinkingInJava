package com.weitongming.Semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by tim.wei on 2017/9/4.
 */
public class Pool<T> {
    private int size ;
    private List<T> items = new ArrayList<T>() ;
    private volatile boolean[] checkouts;
    private Semaphore available;

    public Pool(Class<T> classObject ,int size ){
        this.size = size ;
        checkouts = new boolean[size] ;
        available = new Semaphore(size,true) ;
        for (int i = 0; i < size; i++) {
            try {
                items.add(classObject.newInstance()) ;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 从池中取出
     * @return
     * @throws InterruptedException
     */
    public T checkout() throws InterruptedException {
        available.acquire();
        return getItem() ;
    }

    public  synchronized T getItem(){
        for (int i = 0; i < size; i++) {
            if (!checkouts[i]){
                checkouts[i] = true;
                return items.get(i) ;
            }
        }
        return null;//信号量防止代码执行到这里
    }

    public void checkIn(T x){
        if (releaseItem(x)){
            available.release();
        }
    }

    public synchronized boolean releaseItem(T x){
        int index = items.indexOf(x);
        if (index == -1){
            //不存在
            return false;
        }
        if (checkouts[index]){
            checkouts[index] = false;
            return true;
        }
        return  false ;
    }

}
