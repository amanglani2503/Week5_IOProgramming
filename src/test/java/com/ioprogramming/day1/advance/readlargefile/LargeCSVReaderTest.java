package com.ioprogramming.day1.advance.readlargefile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LargeCSVReaderTest {

    @TempDir
    File tempDir;

    private File testCsvFile;

    @BeforeEach
    void setUp() throws IOException {
        testCsvFile = new File(tempDir, "large_test.csv");

        // Writing test data
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testCsvFile))) {
            writer.write("ID,Name,Age,Marks\n"); // Header

            for (int i = 1; i <= 500; i++) { // Generating 500 rows
                writer.write(i + ",Student" + i + "," + (20 + (i % 10)) + "," + (50 + (i % 50)) + "\n");
            }
        }
    }

    @Test
    void testReadLargeCSV() {
        LargeCSVReader.readLargeCSV(testCsvFile.getAbsolutePath());

        // Since the method prints output but does not return values,
        // you can validate correctness by checking for exceptions (no assertion failures).
        assertTrue(testCsvFile.exists(), "CSV file should exist");
        assertTrue(testCsvFile.length() > 0, "CSV file should not be empty");
    }
}
