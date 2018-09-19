package com.zl.entity;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static  void main(String[] args){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        User user = (User) applicationContext.getBean("user");
        //User user2 = (User) applicationContext.getBean("user");
        Car car = (Car) applicationContext.getBean("car");
        //System.out.println(user == user2);
        //System.out.println(user2);
    }
}
