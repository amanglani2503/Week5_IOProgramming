package com.ioprogramming.day2.handsonpracticeproblems.mergejson;

public class Car {
    private String brand;
    private String model;
    private int launchYear;
    private double price;

    public Car(String brand, String model, int launchYear, double price) {
        this.brand = brand;
        this.model = model;
        this.launchYear = launchYear;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getLaunchYear() {
        return launchYear;
    }

    public double getPrice() {
        return price;
    }
}
