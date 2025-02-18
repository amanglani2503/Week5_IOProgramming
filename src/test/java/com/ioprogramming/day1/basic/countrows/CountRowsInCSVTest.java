package com.ioprogramming.day1.basic.countrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CountRowsInCSVTest {

    private CountRowsInCSV countRowsInCSV;

    @TempDir
    File tempDir;

    @BeforeEach
    void setUp() {
        countRowsInCSV = new CountRowsInCSV();
    }

    @Test
    void testCountRows_ValidFile() throws IOException {
        File tempFile = new File(tempDir, "test.csv");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("ID,Name,Age,Marks\n"); // Header
            writer.write("1,John,25,90\n");
            writer.write("2,Alice,22,85\n");
            writer.write("3,Bob,30,95\n");
        }

        int rowCount = countRowsInCSV.countRows(tempFile.getAbsolutePath());
        assertEquals(3, rowCount); // Excludes header row
    }

    @Test
    void testCountRows_EmptyFile() throws IOException {
        File tempFile = new File(tempDir, "empty.csv");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(""); // Empty file
        }

        int rowCount = countRowsInCSV.countRows(tempFile.getAbsolutePath());
        assertEquals(-1, rowCount); // Header is also missing, so -1 (since --rowCount in the code)
    }

    @Test
    void testCountRows_FileNotFound() {
        String invalidFilePath = tempDir.getAbsolutePath() + "/non_existent.csv";
        int rowCount = countRowsInCSV.countRows(invalidFilePath);
        assertEquals(-1, rowCount); // Should return -1 as the default behavior
    }
}
