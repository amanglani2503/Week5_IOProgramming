package com.ioprogramming.day2.handsonpracticeproblems.jsonarray;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ioprogramming.day2.practiceproblems.jsonarray.Car;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class JSONListTest {
    @Test
    void testJsonSerialization() {
        try {
            List<Car> cars = Arrays.asList(
                    new Car("Tesla", "Model S", 2023, 79999.0),
                    new Car("Tesla", "Model X", 2024, 99999.0),
                    new Car("BMW", "X5", 2022, 65999.0)
            );

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonArray = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cars);

            assertNotNull(jsonArray, "JSON output should not be null");
            assertTrue(jsonArray.contains("Tesla"), "JSON should contain 'Tesla'");
            assertTrue(jsonArray.contains("BMW"), "JSON should contain 'BMW'");
            assertTrue(jsonArray.startsWith("["), "JSON should start with '['");
            assertTrue(jsonArray.endsWith("]"), "JSON should end with ']'");

        } catch (Exception e) {
            fail("Exception thrown during JSON serialization: " + e.getMessage());
        }
    }
}
