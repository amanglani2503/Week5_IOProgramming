package com.ioprogramming.day1.basic.writefile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CSVFileWriterTest {

    private CSVFileWriter csvFileWriter;

    @TempDir
    File tempDir;

    @BeforeEach
    void setUp() {
        csvFileWriter = new CSVFileWriter();
    }

    @Test
    void testWriteCSV_ValidFile() throws IOException {
        File tempFile = new File(tempDir, "test_output.csv");

        csvFileWriter.writeCSV(tempFile.getAbsolutePath());

        assertTrue(tempFile.exists());

        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
            String header = reader.readLine();
            assertNotNull(header);
            assertEquals("\"ID\",\"Name\",\"Department\",\"Salary\"", header);

            String firstRow = reader.readLine();
            assertNotNull(firstRow);
            assertEquals("\"1\",\"Alice\",\"HR\",\"50000\"", firstRow);
        }
    }
}
