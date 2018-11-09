package com.zl.jvm.mashibingdemo;

/**
 * @author zlCalma
 * @date 2018/11/8 17:44.
 */
public class T2 {
    private int count = 10;
    public void m(){
        synchronized (this){
            count--;
            System.out.println(Thread.currentThread().getName()+"count " + count);
        }
    }
}
