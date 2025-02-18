package com.ioprogramming.day2.practiceproblems.jsonarray;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class JSONListConverterTest {

    @Test
    void testJsonArrayConversion() {
        try {
            List<Car> cars = Arrays.asList(
                    new Car("Tesla", "Model S", 2023, 79999.0),
                    new Car("Tesla", "Model X", 2024, 99999.0),
                    new Car("BMW", "X5", 2022, 65999.0)
            );

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonArray = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cars);

            assertNotNull(jsonArray, "Generated JSON array should not be null.");
            assertTrue(jsonArray.startsWith("["), "JSON should start with '[' indicating an array.");
            assertTrue(jsonArray.contains("\"brand\" : \"Tesla\""), "JSON should contain Tesla brand.");
            assertTrue(jsonArray.contains("\"brand\" : \"BMW\""), "JSON should contain BMW brand.");

        } catch (Exception e) {
            fail("Exception should not occur during JSON conversion: " + e.getMessage());
        }
    }
}
