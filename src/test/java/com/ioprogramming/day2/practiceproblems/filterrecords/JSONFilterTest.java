package com.ioprogramming.day2.practiceproblems.filterrecords;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JSONFilterTest {

    @Test
    void testFilteredPersons() throws Exception {
        String jsonData = "[\n" +
                "    { \"name\": \"Alice Johnson\", \"email\": \"alice@example.com\", \"age\": 28, \"city\": \"New York\" },\n" +
                "    { \"name\": \"Bob Smith\", \"email\": \"bob@example.com\", \"age\": 34, \"city\": \"Los Angeles\" },\n" +
                "    { \"name\": \"Charlie Brown\", \"email\": \"charlie@example.com\", \"age\": 25, \"city\": \"Chicago\" }\n" +
                "]";

        ObjectMapper objectMapper = new ObjectMapper();
        List<Person> persons = objectMapper.readValue(jsonData, new TypeReference<List<Person>>() {});

        // Run main method logic manually
        List<Person> filteredPersons = new java.util.ArrayList<>();
        for (Person person : persons) {
            if (person.getAge() > 25) {
                filteredPersons.add(person);
            }
        }

        // Assertions
        assertEquals(2, filteredPersons.size(), "Filtered list should contain 2 persons.");
        assertEquals("Alice Johnson", filteredPersons.get(0).getName(), "First person should be Alice Johnson.");
        assertEquals("Bob Smith", filteredPersons.get(1).getName(), "Second person should be Bob Smith.");
    }

    @Test
    void testFilteredPersons_NoMatch() throws Exception {
        String jsonData = "[\n" +
                "    { \"name\": \"Alice Johnson\", \"email\": \"alice@example.com\", \"age\": 24, \"city\": \"New York\" },\n" +
                "    { \"name\": \"Bob Smith\", \"email\": \"bob@example.com\", \"age\": 25, \"city\": \"Los Angeles\" }\n" +
                "]";

        ObjectMapper objectMapper = new ObjectMapper();
        List<Person> persons = objectMapper.readValue(jsonData, new TypeReference<List<Person>>() {});

        List<Person> filteredPersons = new java.util.ArrayList<>();
        for (Person person : persons) {
            if (person.getAge() > 25) {
                filteredPersons.add(person);
            }
        }

        // Assertions
        assertTrue(filteredPersons.isEmpty(), "Filtered list should be empty when no person is older than 25.");
    }

    @Test
    void testInvalidJson_ThrowsException() {
        String invalidJson = "{ \"name\": \"Alice Johnson\", \"email\": \"alice@example.com\", \"age\": \"twenty-eight\", \"city\": \"New York\" }";

        ObjectMapper objectMapper = new ObjectMapper();

        Exception exception = assertThrows(Exception.class, () -> {
            objectMapper.readValue(invalidJson, new TypeReference<List<Person>>() {});
        });

        assertTrue(exception.getMessage().contains("Cannot deserialize"), "Exception message should indicate JSON parsing failure.");
    }
}
