package com.zl.jvm.mashibingdemo;

/**
 * @author zlCalma
 * @date 2018/11/8 17:57.
 */
public class T5 implements Runnable {
    private int count = 10;

    @Override
    //原子操作，线程不可打断
    public synchronized void   run(){
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args){
        T5 t = new T5();//一个对象多个线程访问
        for(int i=0;i<5;i++){
            new Thread(t,"THREAD " + i).start();
        }
    }
}
