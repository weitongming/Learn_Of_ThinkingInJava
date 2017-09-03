package com.weitongming.Semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by tim.wei on 2017/9/4.
 * 正常的锁（例如concurrent.locks以及synchronized）在任何时刻只能允许一个任务访问一项资源，
 * 而计数信号量允许n个任务同时访问这个资源。
 * 可以将信号量看做是在向外分发使用资源的许可证。
 * 可以考虑对象池的概念，例如数据库连接池
 *
 */
public class Main {
    final static int SIZE = 25;
    public static void main(String args[]) throws InterruptedException {
        final  Pool<Fat> pool = new Pool<>(Fat.class,SIZE) ;
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < SIZE; i++) {
            executorService.execute(new CheckOutTask<Fat>(pool));
        }
        System.out.println("所有的checkout已启动");
        List<Fat> list = new ArrayList<>() ;
        for (int i = 0; i < SIZE; i++) {
            Fat fat = pool.checkout() ;
            System.out.println(i + "main thread check out");
            fat.operation();
            list.add(fat) ;
        }
        Future<?> blocked = executorService.submit(()->{
            try {
                pool.checkout();
            } catch (InterruptedException e) {
                System.out.println("lambda中断啦");
            }
        });
        TimeUnit.SECONDS.sleep(2);
        blocked.cancel(true) ;
        System.out.println("list 归还");
        list.forEach(fat -> pool.checkIn(fat));
        list.forEach(fat -> pool.checkIn(fat));//二次提交忽略
        executorService.shutdownNow();
    }
}
