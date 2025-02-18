package com.ioprogramming.day1.advance.detectduplicates;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class DetectDuplicatesTest {

    private DetectDuplicates detectDuplicates;

    @TempDir
    File tempDir;

    @BeforeEach
    void setUp() {
        detectDuplicates = new DetectDuplicates();
    }

    @Test
    void testDetectDuplicates() throws IOException {
        File tempFile = new File(tempDir, "test.csv");

        // Writing test data to the temporary file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("ID,Name,Age\n");  // Header
            writer.write("1,John,20\n");
            writer.write("2,Alice,22\n");
            writer.write("1,John,20\n");  // Duplicate entry
        }

        // Redirect System.out to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Run the duplicate detection
        detectDuplicates.detectDuplicates(tempFile.getAbsolutePath());

        // Check if duplicate entry is detected
        assertTrue(outputStream.toString().contains("Duplicate record: 1,John,20"));
    }

    @Test
    void testNoDuplicates() throws IOException {
        File tempFile = new File(tempDir, "no_duplicates.csv");

        // Writing test data with no duplicates
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("ID,Name,Age\n");  // Header
            writer.write("1,John,20\n");
            writer.write("2,Alice,22\n");
        }

        // Redirect System.out to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Run the duplicate detection
        detectDuplicates.detectDuplicates(tempFile.getAbsolutePath());

        // Check if no duplicates are detected
        assertFalse(outputStream.toString().contains("Duplicate record"));
    }
}
