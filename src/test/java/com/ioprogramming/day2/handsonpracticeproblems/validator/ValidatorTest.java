package com.ioprogramming.day2.handsonpracticeproblems.validator;

import com.ioprogramming.day2.practiceproblems.validator.Car;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
    @Test
    void testValidJson() {
        String validJson = "{ \"brand\": \"Tesla\", \"model\": \"Model S\", \"launchYear\": 2023, \"price\": 79999.0 }";

        assertDoesNotThrow(() -> Validator.validateJson(validJson), "Valid JSON should not throw an exception");
    }

    @Test
    void testInvalidJson() {
        String invalidJson = "{ \"brand\": \"Tesla\", \"launchYear\": \"twenty-twenty-three\", \"price\": 79999.0 }";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> Validator.validateJson(invalidJson));
        assertEquals("Invalid Json", exception.getMessage(), "Exception message should be 'Invalid Json'");
    }
}
