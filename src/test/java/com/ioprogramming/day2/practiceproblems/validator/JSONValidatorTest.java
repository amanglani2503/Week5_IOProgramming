package com.ioprogramming.day2.practiceproblems.validator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JSONValidatorTest {

    @Test
    void testValidJson() {
        String validJson = "{ \"brand\": \"Tesla\", \"model\": \"Model S\", \"launchYear\": 2023, \"price\": 79999.0 }";

        // Capture console output
        try {
            JSONValidator.validateJson(validJson);
        } catch (Exception e) {
            fail("Valid JSON should not throw an exception.");
        }
    }

    @Test
    void testInvalidJson() {
        String invalidJson = "{ \"brand\": \"Tesla\", \"launchYear\": \"twenty-twenty-three\", \"price\": 79999.0 }";

        Exception exception = assertThrows(Exception.class, () -> {
            JSONValidator.validateJson(invalidJson);
        });

        assertNotNull(exception, "Invalid JSON should cause an exception.");
    }
}
