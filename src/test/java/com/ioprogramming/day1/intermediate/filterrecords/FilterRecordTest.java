package com.ioprogramming.day1.intermediate.filterrecords;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


class FilterRecordTest {

    private FilterRecord filterRecord;

    @TempDir
    File tempDir;

    @BeforeEach
    void setUp() {
        filterRecord = new FilterRecord();
    }

    @Test
    void testFilter_ValidFile() throws IOException {
        File tempFile = new File(tempDir, "test.csv");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("ID,Name,Age,Marks\n"); // Header
            writer.write("1,John,25,90\n");
            writer.write("2,Alice,22,85\n");
            writer.write("3,Bob,30,75\n");
        }

        filterRecord.filter(tempFile.getAbsolutePath());
        // We expect the output to include only records with Marks > 80:
        // Expected output: "1,John,25,90", "2,Alice,22,85"
    }

    @Test
    void testFilter_NoRecordsAbove80() throws IOException {
        File tempFile = new File(tempDir, "test.csv");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("ID,Name,Age,Marks\n"); // Header
            writer.write("1,John,25,70\n");
            writer.write("2,Alice,22,60\n");
            writer.write("3,Bob,30,50\n");
        }

        filterRecord.filter(tempFile.getAbsolutePath());
        // Expected output: No records should be printed since all marks are <= 80
    }

    @Test
    void testFilter_EmptyFile() throws IOException {
        File tempFile = new File(tempDir, "empty.csv");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(""); // Empty file
        }

        filterRecord.filter(tempFile.getAbsolutePath());
        // Expected output: No records should be printed due to an empty file
    }
}
