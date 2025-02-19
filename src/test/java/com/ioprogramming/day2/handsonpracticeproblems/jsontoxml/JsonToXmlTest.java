package com.ioprogramming.day2.handsonpracticeproblems.jsontoxml;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class JsonToXmlTest {

    private static final String JSON_FILE_PATH = "E:\\Files\\Week5\\day2\\student.json";
    private ObjectMapper objectMapper;
    private XmlMapper xmlMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        xmlMapper = new XmlMapper();
    }

    @Test
    void testJsonToXmlConversion() {
        try {
            assertTrue(new File(JSON_FILE_PATH).exists(), "JSON file does not exist");

            JsonNode jsonNode = objectMapper.readTree(new File(JSON_FILE_PATH));

            String xmlOutput = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);

            assertNotNull(xmlOutput, "XML output should not be null");
            assertFalse(xmlOutput.isEmpty(), "XML output should not be empty");

            System.out.println("Generated XML:\n" + xmlOutput);

        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}
