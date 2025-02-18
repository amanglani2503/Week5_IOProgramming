package com.ioprogramming.day2.practiceproblems.jsonreader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JSONReadTest {

    @Test
    void testJSONReading() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            File testFile = new File("src/test/resources/Employee.json");

            JsonNode rootNode = objectMapper.readTree(testFile);

            String name = rootNode.get(0).get("name").asText();
            String email = rootNode.get(0).get("email").asText();

            assertEquals("Alice Johnson", name);
            assertEquals("alice@example.com", email);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
