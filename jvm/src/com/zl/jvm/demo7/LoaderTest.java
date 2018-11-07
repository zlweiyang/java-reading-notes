package com.zl.jvm.demo7;

/**
 * @author zlCalma
 * @date 2018/11/7 16:19.
 */
public class LoaderTest {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader loader = HelloWorld.class.getClassLoader();
        System.out.println(loader);
        loader.loadClass("Test2");
        Class.forName("Test2");
    }
}
