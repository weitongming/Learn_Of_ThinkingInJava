package com.weitongming.CyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by tim.wei on 2017/9/3.
 */
public class Horse implements  Runnable {
    private static int count = 0;
    private  final int id = count ++ ;
    private int strides  = 0;
    private static Random random = new Random(47) ;
    private CyclicBarrier barrier ;
    public Horse(CyclicBarrier cyclicBarrier){
        barrier = cyclicBarrier ;
    }
    public synchronized int getStrides(){
        return strides;
    }
    @Override
    public void run() {
        while (!Thread.interrupted()){
            synchronized (this){
                int step = random.nextInt(4) ;
                strides +=  step;
            }
            try {
                barrier.await();
            } catch (InterruptedException e) {

            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String toString() {
        return "Horse : " + id;
    }
    public String tracks(){
        StringBuilder sb = new StringBuilder() ;
        for (int i = 0 ; i < strides ; i++){
            sb.append("*");
        }
        sb.append(id);
        return sb.toString();
    }
}
