package com.zl.Concurrency.demo4;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.lang.Thread;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Deprecqated {

    public static void main(String[] args)throws Exception{
           DateFormat format = new SimpleDateFormat("HH:mm:sss");
           Thread printThread = new Thread(new Runner(),"PrintThread");
           printThread.setDaemon(true);
           printThread.start();
           TimeUnit.SECONDS.sleep(3);
           printThread.suspend();
           System.out.println("main suspend PrintThread at " + format.format(new Date()));
           TimeUnit.SECONDS.sleep(3);
           printThread.resume();
           System.out.println("main resume PrintThread at" + format.format(new Date()));
           TimeUnit.SECONDS.sleep(3);
           printThread.stop();
           System.out.println("main stop PrintThread at" + format.format(new Date()));
           TimeUnit.SECONDS.sleep(3);
    }

    static class Runner implements Runnable{
        @Override
        public void run() {
            DateFormat format = new SimpleDateFormat("HH:mm:ss");
            while (true){
                System.out.println(Thread.currentThread().getName() +
                        "Run at "+format.format(new Date()));
                SleepUtils.second(1);
            }
        }
    }
}
