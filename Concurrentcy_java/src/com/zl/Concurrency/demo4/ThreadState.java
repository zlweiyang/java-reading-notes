package com.zl.Concurrency.demo4;

public class ThreadState {

    public static void main(String[] args){

        new Thread(new TimeWaiting(),"TimeWaitingThread").start();
        new Thread(new Waiting(),"WaitingThread").start();
        //使用两个Blocked线程，一个获得锁成功，另一个被阻塞
        new Thread(new Blocked(),"BlockedThread-1").start();
        new Thread(new Blocked(),"BlockedThread-2").start();

    }
    //该线程不断的进行睡眠
    static class TimeWaiting implements Runnable{
        @Override
        public void run() {

            while (true){
                SleepUtils.second(100);
            }
        }
    }
    //该线程在Waiting实例上等待
    static class Waiting implements Runnable{
        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //该线程在Blocked.class实例加锁后，不会释放该锁
    static class Blocked implements Runnable{
        @Override
        public void run() {
            synchronized (Blocked.class){
                while (true){
                    SleepUtils.second(100);
                }
            }
        }
    }

}
