package com.zl.entity;

public class Car {

    private int id;
    private String brand;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public  Car(){
        System.out.println("创建了Car对象");
    }
}
