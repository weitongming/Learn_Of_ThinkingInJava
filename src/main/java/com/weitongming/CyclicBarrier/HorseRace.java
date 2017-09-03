package com.weitongming.CyclicBarrier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by tim.wei on 2017/9/3.
 */
public class HorseRace {
    static final int FINISH_LINE  = 75;
    private List<Horse> horses = new ArrayList<>() ;
    private ExecutorService executorService = Executors.newCachedThreadPool() ;
    private CyclicBarrier barrier ;
    public HorseRace(int nHorses , final int pause){
        barrier = new CyclicBarrier(nHorses , ()->{
            StringBuilder sb = new StringBuilder() ;
            for (int i = 0 ; i < FINISH_LINE ; i++){
                sb.append("=") ;
            }
            System.out.println(sb);
            horses.forEach(horse -> System.out.println(horse.tracks()));
            horses.forEach(horse -> {
                if (horse.getStrides() >= FINISH_LINE){
                    System.out.println(horse + "won");
                    executorService.shutdownNow();
                    return;
                }
            });
            try {
                TimeUnit.MILLISECONDS.sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        for (int i = 0; i < nHorses; i++) {
            Horse horse = new Horse(barrier);
            horses.add(horse) ;
            executorService.execute(horse);
        }
    }

}
