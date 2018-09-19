package com.zl.springdemo.entity;

import java.util.HashMap;
import java.util.Map;

public class InstanceCarFactory {

    private Map<Integer,Car> cars;

    public InstanceCarFactory(){
        cars = new HashMap<>();
        cars.put(1,new Car(1,"奥迪"));
        cars.put(2,new Car(2,"奥拓"));
    }

    public Car getCar(int num){
        return cars.get(num);
    }
}
