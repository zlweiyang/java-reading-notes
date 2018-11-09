package com.zl.jvm.mashibingdemo;

/**
 * @author zlCalma
 * @date 2018/11/8 17:53.
 */
public class T4 {
    private static int count = 10;

    public synchronized static void m(){//等同于synchronized(T.class)
        count--;
        System.out.println(Thread.currentThread().getName() + "count=" + count);
    }

    public static void mm(){
        synchronized (T4.class){//不可锁定this
            count--;
        }
    }
}
