package com.ioprogramming.day1.intermediate.sortrecords;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortRecordsTest {

    private SortRecords sortRecords;

    @TempDir
    File tempDir;

    @BeforeEach
    void setUp() {
        sortRecords = new SortRecords();
    }

    @Test
    void testSortAndPrintTop5_ValidFile() throws IOException {
        File tempFile = new File(tempDir, "test.csv");
        File outputFile = new File(tempDir, "sortedData.csv"); // Output file path

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("ID,Name,Department,Salary\n"); // Header
            writer.write("1,John,HR,50000\n");
            writer.write("2,Alice,IT,70000\n");
            writer.write("3,Bob,IT,80000\n");
            writer.write("4,David,Marketing,60000\n");
            writer.write("5,Emma,Sales,55000\n");
            writer.write("6,Charlie,Finance,75000\n");
        }

        sortRecords.sortAndStoreTop5(tempFile.getAbsolutePath(), outputFile.getAbsolutePath());

        assertTrue(outputFile.exists());

        // Read the content to verify sorting
        List<String> lines = Files.readAllLines(outputFile.toPath());
        assertTrue(lines.size() > 1); // At least 1 line of data should be printed after sorting
    }

    @Test
    void testSortAndPrintTop5_EmptyFile() throws IOException {
        File tempFile = new File(tempDir, "empty.csv");
        File outputFile = new File(tempDir, "sortedData.csv"); // Output file path

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(""); // Empty file
        }

        sortRecords.sortAndStoreTop5(tempFile.getAbsolutePath(), outputFile.getAbsolutePath());

        // Ensure the output file exists
        assertTrue(outputFile.exists());

        // Verify that the output file is empty
        List<String> lines = Files.readAllLines(outputFile.toPath());
        assertTrue(lines.isEmpty()); // Output should be empty
    }
}
