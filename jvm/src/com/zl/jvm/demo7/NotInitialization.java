package com.zl.jvm.demo7;

/**
 * @author zlCalma
 * @date 2018/10/30 21:26.
 */
public class NotInitialization {

    public static void main(String[] args){

        //子类直接调用父类的类变量value
        //System.out.println(SubClass.value);
        //打印结果：SuperClass init!
        //123，只打印出父类的静态代码块的内容
        //原因：对于静态字段，只有直接定义这个字段的类才会被初始化，
        // 因此通过其子类来引用父类中定义的静态字段，只会触发父类初始化

        /*--------------------------------------------------------------------------------*/
        //SuperClass[] sca = new SuperClass[10];
        System.out.println(ConstClass.HELLOWORLD);
    }
}
