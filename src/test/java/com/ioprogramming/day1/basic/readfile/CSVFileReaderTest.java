package com.ioprogramming.day1.basic.readfile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CSVFileReaderTest {

    private CSVFileReader csvFileReader;

    @TempDir
    File tempDir;

    @BeforeEach
    void setUp() {
        csvFileReader = new CSVFileReader();
    }

    @Test
    void testReadCsv_ValidFile() throws IOException {
        File tempFile = new File(tempDir, "test.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("1,John,25,90\n");
            writer.write("2,Alice,22,85\n");
        }

        assertDoesNotThrow(() -> csvFileReader.readcsv(tempFile.getAbsolutePath()));
    }

    @Test
    void testReadCsv_FileNotFound() {
        String invalidFilePath = tempDir.getAbsolutePath() + "/non_existent.csv";
        assertDoesNotThrow(() -> csvFileReader.readcsv(invalidFilePath));
    }
}
