package com.ioprogramming.day2.practiceproblems.carjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CarJSONTest {

    @Test
    void testCarSerialization() {
        try {

            Car car = new Car("Tesla", "Model S", 2023, 79999);

            ObjectMapper objectMapper = new ObjectMapper();

            String carJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(car);

            assertTrue(carJson.contains("Tesla"));
            assertTrue(carJson.contains("Model S"));
            assertTrue(carJson.contains("2023"));
            assertTrue(carJson.contains("79999"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}