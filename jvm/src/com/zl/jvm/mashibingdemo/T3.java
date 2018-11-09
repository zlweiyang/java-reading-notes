package com.zl.jvm.mashibingdemo;

/**
 * @author zlCalma
 * @date 2018/11/8 17:50.
 */
public class T3 {
    private int count = 10;

    public synchronized void m(){
        count--;
        System.out.println(Thread.currentThread().getName() +"count=" + count);
    }
}
