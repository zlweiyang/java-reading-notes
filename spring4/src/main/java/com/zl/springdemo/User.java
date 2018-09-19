package com.zl.springdemo;

public class User {

    private int id;
    private String name;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(){
        System.out.println("创建user对象");
    }

    public User(int id,String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User " + "id = " + id +",name=" +name + ",age="+age ;
    }
}
