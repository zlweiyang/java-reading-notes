package com.zl.jvm.mashibingdemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zlCalma
 * @date 2018/11/23 22:12.
 */
public class ThreadDemo {

    static class Hello {
        static {
            if (true) {
                System.out.println(Thread.currentThread().getName() + " init");
                while (true) {

                }
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(20);

        int i=0;
        while (i++ < 20){
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ".....start");
                    //ThreadDemo d = new ThreadDemo();
                    Hello h = new Hello();
                    System.out.println(Thread.currentThread().getName() + ".....stop");
                }
            });
        }
    }

}
