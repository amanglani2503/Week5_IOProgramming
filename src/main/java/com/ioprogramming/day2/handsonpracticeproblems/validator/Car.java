package com.ioprogramming.day2.handsonpracticeproblems.validator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Car {
    private String brand;
    private String model;
    private int launchYear;
    private double price;

    public Car(){
        // default constructore needed for deserialization
    }

    @JsonCreator
    public Car(@JsonProperty("brand") String brand, @JsonProperty("model") String model, @JsonProperty("launchYear") int launchYear, @JsonProperty("price") double price) {
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
