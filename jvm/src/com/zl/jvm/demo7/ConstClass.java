package com.zl.jvm.demo7;

/**
 * @author zlCalma
 * @date 2018/10/30 21:36.
 */
public class ConstClass {

    static {
        System.out.println("ConstClass init!");
    }

    //存储在常量池中
    public static final String HELLOWORLD = "hello world";
}
