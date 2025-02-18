package com.ioprogramming.day1.advance.jsoncsvconverter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.nio.file.Files;

class JsonCSVConverterTest {
    @Test
    void testConvertJsonToCsv() throws IOException {
        JsonCSVConverter converter = new JsonCSVConverter();

        // Temporary files for JSON and CSV
        File tempJsonFile = File.createTempFile("test", ".json");
        File tempCsvFile = File.createTempFile("test", ".csv");

        // Sample JSON content
        String jsonContent = "[{\"id\": \"1\", \"name\": \"John\", \"age\": \"30\", \"marks\": \"80\"}]";
        Files.write(tempJsonFile.toPath(), jsonContent.getBytes());

        // Convert JSON to CSV
        converter.convertJsonToCsv(tempJsonFile.getAbsolutePath(), tempCsvFile.getAbsolutePath());

        // Simple check to ensure CSV file exists
        assertTrue(Files.exists(tempCsvFile.toPath()));

        // Read CSV content
        try (BufferedReader reader = new BufferedReader(new FileReader(tempCsvFile))) {
            String line = reader.readLine();  // Read header line
            assertNotNull(line);  // Check if header is not null
        }
    }

    @Test
    void testConvertCsvToJson() throws IOException {
        JsonCSVConverter converter = new JsonCSVConverter();

        // Temporary files for CSV and JSON
        File tempCsvFile = File.createTempFile("test", ".csv");
        File tempJsonFile = File.createTempFile("test", ".json");

        // Sample CSV content
        String csvContent = "ID,Name,Age,Marks\n1,John,30,80";
        Files.write(tempCsvFile.toPath(), csvContent.getBytes());

        // Convert CSV to JSON
        converter.convertCsvToJson(tempCsvFile.getAbsolutePath(), tempJsonFile.getAbsolutePath());

        // Simple check to ensure JSON file exists
        assertTrue(Files.exists(tempJsonFile.toPath()));

        // Read JSON content and perform basic check
        try (BufferedReader reader = new BufferedReader(new FileReader(tempJsonFile))) {
            String jsonContent = reader.readLine();  // Read the first line of JSON content
            assertNotNull(jsonContent);  // Check if JSON content is not null
        }
    }
}
