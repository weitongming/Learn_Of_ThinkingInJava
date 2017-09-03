package com.weitongming.ConsummerAndProductor;

import lombok.Data;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by weitongming on 2017/9/2.
 */
@Data
public class Restaurant {
    private Meal meal  = null;
    private WaitPerson waitPerson = new WaitPerson(this);
    private Chef chef = new Chef(this);
    private ExecutorService executorService  = Executors.newCachedThreadPool();

    public  Restaurant(){
        executorService.execute(waitPerson);
        executorService.execute(chef);
    }

   public static void main(String args[]){
       new Restaurant();
   }
}
