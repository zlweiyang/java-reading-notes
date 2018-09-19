package com.zl.springdemo;

public class Student {

    private int id;
    private String name;
    private int age;
    private Classes classes;

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

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

    @Override
    public String toString() {
        return "Student [id=" + id +", name="+name +",age="+age + ",  classes=" + classes + "]";
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(){
        //this.name = name;
        //this.age = age;
        //this.id = id;
        System.out.println("无参构造");
    }

    public Student(int id,String name,int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
