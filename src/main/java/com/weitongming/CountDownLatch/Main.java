package com.weitongming.CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by tim.wei on 2017/9/3.
 * CountDOwnLatch 用来同步一个或者多个任务，强制他们等待由其他任务执行的一组操作完成
 */
public class Main {
    private  static int size = 100 ;

    public static void main(String args[]) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool() ;
        CountDownLatch latch = new CountDownLatch(size) ;

        for (int i = 0 ; i < size - 1; i++){
            executorService.execute(new TaskPortion(latch));
        }
        for (int i = 0 ; i < 10 ; i++){
            executorService.execute(new WaitingTask(latch));
        }
        System.out.println("所有任务已启动");
        TimeUnit.SECONDS.sleep(20);
        executorService.shutdownNow();
    }
}
