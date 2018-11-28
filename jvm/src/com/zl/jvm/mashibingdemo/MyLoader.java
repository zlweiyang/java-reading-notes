package com.zl.jvm.mashibingdemo;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zlCalma
 * @date 2018/11/24 21:45.
 */
public class MyLoader {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader mycl = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                //return super.loadClass(name);
                //
                String filename = name.substring(name.indexOf(".")+1) +".class";
                InputStream ins = getClass().getResourceAsStream(filename);
                if(ins == null){
                    return super.loadClass(name);
                }
                try {
                    byte[] buff = new byte[ins.available()];
                    ins.read(buff);

                    return defineClass(name,buff,0,buff.length);
                } catch (IOException e) {
                    throw  new ClassNotFoundException();
                }
            }
        };
        Object c = mycl.loadClass("package com.zl.jvm.mashibingdemo.Myloader").newInstance();

        System.out.println(c.getClass());
        System.out.println(c instanceof MyLoader);
    }
}
