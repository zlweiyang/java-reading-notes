package com.zl.jvm.demo7;

/**
 * @author zlCalma
 * @date 2018/10/30 21:22.
 */
public class SuperClass {

    //静态代码块
    static {
        System.out.println("SuperClass init!");
    }
    //静态变量，类变量public权限被子类继承
    public static int value = 123;
}
