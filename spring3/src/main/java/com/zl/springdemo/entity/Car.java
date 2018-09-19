package com.zl.springdemo.entity;

public class Car {

    private int num;
    private String brand;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    public Car(){

    }

    public Car(int num,String brand){
        this.num = num;
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Car[num = " +num + ",barnd = " + brand + "]" ;
    }
}
