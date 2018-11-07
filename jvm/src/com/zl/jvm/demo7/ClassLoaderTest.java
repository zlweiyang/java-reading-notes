package com.zl.jvm.demo7;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zlCalma
 * @date 2018/11/7 15:08.
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try{
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream is = getClass().getResourceAsStream(fileName);
                if (is == null){
                    return super.loadClass(name);
                }
                byte[] b =  new byte[is.available()];
                is.read(b);
                return defineClass(name,b,0,b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj = myLoader.loadClass("org.fenixsoft.classloading.ClassTest").newInstance();

        System.out.println(obj.getClass());
        System.out.println(false);
    }
}
