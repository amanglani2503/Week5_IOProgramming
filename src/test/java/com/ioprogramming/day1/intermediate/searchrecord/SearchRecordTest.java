package com.ioprogramming.day1.intermediate.searchrecord;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class SearchRecordTest {

    private SearchRecord searchRecord;

    @TempDir
    File tempDir;

    @BeforeEach
    void setUp() {
        searchRecord = new SearchRecord();
    }

    @Test
    void testSearch_ValidName() throws IOException {
        File tempFile = new File(tempDir, "test.csv");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("ID,Name,Age,Marks\n"); // Header
            writer.write("1,John,25,90\n");
            writer.write("2,Alice,22,85\n");
            writer.write("3,Charlie,30,95\n");
        }

        searchRecord.search(tempFile.getAbsolutePath(), "Charlie");
        // Expected output: "3,Charlie,30,95" should be printed
    }

    @Test
    void testSearch_NameNotFound() throws IOException {
        File tempFile = new File(tempDir, "test.csv");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("ID,Name,Age,Marks\n"); // Header
            writer.write("1,John,25,90\n");
            writer.write("2,Alice,22,85\n");
        }

        searchRecord.search(tempFile.getAbsolutePath(), "Charlie");
        // Expected output: No records should be printed since "Charlie" is not found
    }

    @Test
    void testSearch_EmptyFile() throws IOException {
        File tempFile = new File(tempDir, "empty.csv");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(""); // Empty file
        }

        searchRecord.search(tempFile.getAbsolutePath(), "Charlie");
        // Expected output: No records should be printed due to an empty file
    }
}
