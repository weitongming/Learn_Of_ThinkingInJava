package com.weitongming.CyclicBarrier;

/**
 * Created by tim.wei on 2017/9/3.
 * 创建一组任务，他们并行的执行工作，然后再进行下一个步骤之前等待，
 */
public class Main {
    public static void main(String args[]){
        int nHorse = 7 ;
        int pause = 200 ;
        new HorseRace(nHorse , pause);
    }
}
