package com.ioprogramming.day2.practiceproblems.validator;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONValidator {
    public static void validateJson(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            Car car = objectMapper.readValue(json, Car.class);
            System.out.println("Valid Json");
        } catch (Exception e){
            throw new IllegalArgumentException("Invalid Json");
        }
    }

    public static void main(String[] args) {
        try{
            String validJson = "{ \"brand\": \"Tesla\", \"model\": \"Model S\", \"launchYear\": 2023, \"price\": 79999.0 }";
            String invalidJson = "{ \"brand\": \"Tesla\", \"launchYear\": \"twenty-twenty-three\", \"price\": 79999.0 }";

            validateJson(validJson);
            validateJson(invalidJson);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
