package com.ioprogramming.day2.handsonpracticeproblems.dbtojson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReportGeneratorTest {

    @Test
    void testGenerateJsonReport() throws IOException {
        // Sample Employees
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice Johnson", "IT", 60000),
                new Employee(2, "Bob Smith", "HR", 55000),
                new Employee(3, "Charlie Brown", "Finance", 70000)
        );

        // Temporary file for testing
        File tempFile = File.createTempFile("test_employee_report", ".json");
        tempFile.deleteOnExit();  // Delete after test execution

        // Generate JSON report
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.generateJsonReport(employees, tempFile.getAbsolutePath());

        // Validate JSON file exists
        assertTrue(tempFile.exists(), "JSON file should be created");

        // Read and validate JSON content
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonArray = objectMapper.readTree(tempFile);

        assertEquals(3, jsonArray.size(), "JSON should contain 3 employee records");

        // Validate first employee data
        JsonNode firstEmployee = jsonArray.get(0);
        assertEquals(1, firstEmployee.get("employee_id").asInt());
        assertEquals("Alice Johnson", firstEmployee.get("name").asText());
        assertEquals("IT", firstEmployee.get("department").asText());
        assertEquals(60000, firstEmployee.get("salary").asInt());
    }
}
