package com.zl.springdemo.entity;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class Test {

    public static void main(String[] args)throws SQLException{
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
       // Car car2 = (Car) applicationContext.getBean("car2");

        Person person = (Person) applicationContext.getBean("person");

        System.out.println(person);
    }
}
