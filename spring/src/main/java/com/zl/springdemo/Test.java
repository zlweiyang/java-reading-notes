package com.zl.springdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args){
        //Student student = new Student();
        //System.out.println(student);
        //加载spring.xml配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
//        //通过id获取对象
//        Student stu = (Student)applicationContext.getBean("stu");
//        System.out.println(stu);
        Classes classes = (Classes) applicationContext.getBean("classes");
        System.out.println(classes);
        //通过运行时类获取对象
        //当有两个bean都是由Student类生成时，程序报错，IOC容器无法将两个bean都返回，必须指定唯一的bean
//        Student stu = applicationContext.getBean(Student.class);
//        System.out.println(stu);

    }
}
