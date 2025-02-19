package com.ioprogramming.day2.handsonpracticeproblems.jsonarray;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ioprogramming.day2.practiceproblems.jsonarray.Car;

import java.util.Arrays;
import java.util.List;

public class JSONList {
    public static void main(String[] args) {
        try{
            List<com.ioprogramming.day2.practiceproblems.jsonarray.Car> cars = Arrays.asList(
                    new com.ioprogramming.day2.practiceproblems.jsonarray.Car("Tesla", "Model S", 2023, 79999.0),
                    new com.ioprogramming.day2.practiceproblems.jsonarray.Car("Tesla", "Model X", 2024, 99999.0),
                    new Car("BMW", "X5", 2022, 65999.0)
            );

            ObjectMapper objectMapper = new ObjectMapper();

            String jsonArray = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cars);

            System.out.println("JSON Array :-");
            System.out.println(jsonArray);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}