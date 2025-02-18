package com.ioprogramming.day1.advance.datavalidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = new Validator();
    }

    @Test
    void testValidData() {
        List<String[]> records = Arrays.asList(
                new String[]{"ID", "Name", "Department", "Email", "Phone"},
                new String[]{"1", "John", "HR", "john.doe@example.com", "1234567890"},
                new String[]{"2", "Alice", "IT", "alice.smith@example.com", "9876543210"}
        );

        for (int i = 1; i < records.size(); i++) {
            String[] record = records.get(i);
            String email = record[3];
            String phone = record[4];

            assertTrue(validator.isValidEmail(email));
            assertTrue(validator.isValidPhoneNumber(phone));
        }
    }

    @Test
    void testInvalidEmail() {
        List<String[]> records = Arrays.asList(
                new String[]{"ID", "Name", "Department", "Email", "Phone"},
                new String[]{"1", "John", "HR", "invalid-email", "1234567890"}
        );

        String[] record = records.get(1);
        String email = record[3];

        assertFalse(validator.isValidEmail(email));
    }

    @Test
    void testInvalidPhone() {
        List<String[]> records = Arrays.asList(
                new String[]{"ID", "Name", "Department", "Email", "Phone"},
                new String[]{"1", "John", "HR", "john.doe@example.com", "12345"}
        );

        String[] record = records.get(1);
        String phone = record[4];

        assertFalse(validator.isValidPhoneNumber(phone));
    }
}