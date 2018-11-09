package com.zl.jvm.mashibingdemo;

/**
 * @author zlCalma
 * @date 2018/11/8 17:48.
 */
public class T1 {

    private int count = 10;
    Object o = new Object();

    public void m(){
        synchronized (o){
            count--;
            System.out.println(Thread.currentThread().getName() + "name = " + "count =" + count );
        }
    }
}
