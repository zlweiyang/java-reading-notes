package com.zl.Concurrency.demo4;

import java.util.concurrent.TimeUnit;

public class Daemon {
    public static void main(String[] args){
        Thread thread = new Thread(new DaemonRunner());
        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunner implements Runnable{
        @Override
        public void run() {
            try{
                SleepUtils.second(10);
            }finally {
                System.out.println("DaemonThread finally run.");
            }
        }
    }
}
