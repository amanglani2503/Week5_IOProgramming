package com.ioprogramming.day2.practiceproblems.carjson;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CarJSON {
    public static void main(String[] args) {
        try{
            Car car = new Car("Tesla", "Model S", 2023, 79999);

            ObjectMapper objectMapper = new ObjectMapper();

            String carJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(car);

            System.out.println("JSON Object for Car :");
            System.out.println(carJson);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
