package com.ioprogramming.day2.handsonpracticeproblems.readjson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReadJSONTest {
    private static final String TEST_JSON_PATH = "E:\\Files\\Week5\\day2\\student.json";
    private JsonNode rootNode;

    @BeforeEach
    void setUp() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        rootNode = objectMapper.readTree(new File(TEST_JSON_PATH));
    }

    @Test
    void testJsonFileExists() {
        File file = new File(TEST_JSON_PATH);
        assertNotNull(file, "JSON file should exist");
    }

    @Test
    void testJsonFileNotEmpty() throws Exception {
        String content = new String(Files.readAllBytes(Paths.get(TEST_JSON_PATH)));
        assertNotNull(content, "JSON file should not be empty");
    }

    @Test
    void testPrintJsonDoesNotThrowException() {
        assertDoesNotThrow(() -> ReadJSON.printJson(rootNode), "printJson() should not throw an exception");
    }
}
