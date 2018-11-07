package com.zl.jvm.demo7;

/**
 * @author zlCalma
 * @date 2018/11/7 15:24.
 */
public class FindClassLoader {
    public static void main(String[] args){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());
    }
}
