package com.ioprogramming.day1.intermediate.modifyrecord;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModifyRecordTest {

    private ModifyRecord modifyRecord;

    @TempDir
    File tempDir;

    @BeforeEach
    void setUp() {
        modifyRecord = new ModifyRecord();
    }

    @Test
    void testModify_ValidFile() throws IOException {
        File inputFile = new File(tempDir, "input.csv");
        File outputFile = new File(tempDir, "output.csv");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile))) {
            writer.write("ID,Name,Department,Salary\n"); // Header
            writer.write("1,John,HR,50000\n");
            writer.write("2,Alice,IT,70000\n");
            writer.write("3,Bob,IT,65000\n");
        }

        modifyRecord.modify(inputFile.getAbsolutePath(), outputFile.getAbsolutePath());

        assertTrue(outputFile.exists());

        List<String> lines = Files.readAllLines(Paths.get(outputFile.getAbsolutePath()));
        assertTrue(lines.size() > 1); // File has at least 1 updated record

        String updatedSalaryLine = lines.stream()
                .filter(line -> line.contains("Alice"))
                .findFirst().orElse("");

        assertTrue(updatedSalaryLine.contains("77000")); // IT department salary should be updated by 10%
    }

    @Test
    void testModify_EmptyFile() throws IOException {
        File inputFile = new File(tempDir, "empty.csv");
        File outputFile = new File(tempDir, "output.csv");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile))) {
            writer.write(""); // Empty file
        }

        modifyRecord.modify(inputFile.getAbsolutePath(), outputFile.getAbsolutePath());

        assertTrue(outputFile.exists());
        List<String> lines = Files.readAllLines(Paths.get(outputFile.getAbsolutePath()));
        assertTrue(lines.isEmpty()); // Output file should be empty as well
    }
}
