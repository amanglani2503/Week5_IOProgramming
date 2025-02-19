package com.ioprogramming.day2.handsonpracticeproblems.csvtojson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class CsvToJsonTest {

    private File tempJsonFile;

    @BeforeEach
    void setUp() throws IOException {
        tempJsonFile = File.createTempFile("test-employee", ".json");
        tempJsonFile.deleteOnExit();

        String jsonContent = "[\n" +
                "    { \"name\": \"Alice Johnson\", \"email\": \"alice@example.com\", \"age\": 28, \"city\": \"New York\" },\n" +
                "    { \"name\": \"Bob Smith\", \"email\": \"bob@example.com\", \"age\": 34, \"city\": \"Los Angeles\" },\n" +
                "    { \"name\": \"Charlie Brown\", \"email\": \"charlie@example.com\", \"age\": 25, \"city\": \"Chicago\" }\n" +
                "]";

        // Write JSON content to temp file
        try (FileWriter writer = new FileWriter(tempJsonFile)) {
            writer.write(jsonContent);
        }
    }

    @Test
    void testReadJsonFile() throws IOException {
        // Read JSON using ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(tempJsonFile);

        // Assertions
        assertNotNull(rootNode);
        assertTrue(rootNode.isArray());
        assertEquals(3, rootNode.size()); // Expecting 3 records

        // Check first employee details
        assertEquals("Alice Johnson", rootNode.get(0).get("name").asText());
        assertEquals("alice@example.com", rootNode.get(0).get("email").asText());
        assertEquals(28, rootNode.get(0).get("age").asInt());
        assertEquals("New York", rootNode.get(0).get("city").asText());

        assertEquals("Bob Smith", rootNode.get(1).get("name").asText());
        assertEquals("bob@example.com", rootNode.get(1).get("email").asText());

        assertEquals("Charlie Brown", rootNode.get(2).get("name").asText());
    }
}
