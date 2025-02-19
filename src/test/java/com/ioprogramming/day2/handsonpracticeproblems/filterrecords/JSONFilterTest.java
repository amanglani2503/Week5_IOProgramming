package com.ioprogramming.day2.handsonpracticeproblems.filterrecords;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ioprogramming.day2.practiceproblems.filterrecords.Person;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class JSONFilterTest {
    private static final String JSON_DATA = "[\n" +
            "    { \"name\": \"Alice Johnson\", \"email\": \"alice@example.com\", \"age\": 28, \"city\": \"New York\" },\n" +
            "    { \"name\": \"Bob Smith\", \"email\": \"bob@example.com\", \"age\": 34, \"city\": \"Los Angeles\" },\n" +
            "    { \"name\": \"Charlie Brown\", \"email\": \"charlie@example.com\", \"age\": 25, \"city\": \"Chicago\" }\n" +
            "]";

    @Test
    void testFilteringRecords() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Deserialize JSON string into List<Person>
            List<Person> persons = objectMapper.readValue(JSON_DATA, new TypeReference<List<Person>>() {});

            // Apply filtering (age > 25)
            List<Person> filteredPersons = new ArrayList<>();
            for (Person person : persons) {
                if (person.getAge() > 25) {
                    filteredPersons.add(person);
                }
            }

            // Convert filtered list back to JSON
            String filteredJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(filteredPersons);

            // Assertions
            assertNotNull(filteredPersons, "Filtered list should not be null");
            assertEquals(2, filteredPersons.size(), "There should be 2 persons after filtering");
            assertTrue(filteredJson.contains("Alice Johnson"), "Filtered JSON should contain 'Alice Johnson'");
            assertTrue(filteredJson.contains("Bob Smith"), "Filtered JSON should contain 'Bob Smith'");
            assertFalse(filteredJson.contains("Charlie Brown"), "Filtered JSON should not contain 'Charlie Brown'");

        } catch (Exception e) {
            fail("Exception thrown during JSON filtering: " + e.getMessage());
        }
    }
}
