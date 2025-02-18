package com.ioprogramming.day1.advance.mergecsvfiles;

import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.util.List;

import com.opencsv.CSVReader;
import static org.junit.jupiter.api.Assertions.*;

class MergeCSVFilesTest {

    private MergeCSVFiles mergeCSVFiles;

    @TempDir
    File tempDir;

    @BeforeEach
    void setUp() {
        mergeCSVFiles = new MergeCSVFiles();
    }

    @Test
    void testMergeCSVFiles_ValidFiles() throws IOException {
        File file1 = new File(tempDir, "students1.csv");
        File file2 = new File(tempDir, "students2.csv");
        File outputFile = new File(tempDir, "merged_students.csv");

        // Writing data to students1.csv
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file1))) {
            writer.write("ID,Name,Age\n");
            writer.write("1,John,20\n");
            writer.write("2,Alice,22\n");
        }

        // Writing data to students2.csv
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file2))) {
            writer.write("ID,Marks,Grade\n");
            writer.write("1,85,A\n");
            writer.write("3,90,B\n");
        }

        // Merge the CSV files
        mergeCSVFiles.mergeCSVFiles(file1.getAbsolutePath(), file2.getAbsolutePath(), outputFile.getAbsolutePath());

        // Read the merged file
        try (CSVReader csvReader = new CSVReader(new FileReader(outputFile))) {
            List<String[]> records = csvReader.readAll();

            // Validate header
            assertArrayEquals(new String[]{"ID", "Name", "Age", "Marks", "Grade"}, records.get(0));

            // Validate merged data
            assertArrayEquals(new String[]{"1", "John", "20", "85", "A"}, records.get(1));
            assertArrayEquals(new String[]{"2", "Alice", "22", "", ""}, records.get(3));
            assertArrayEquals(new String[]{"3", "", "", "90", "B"}, records.get(2));

            // Validate number of rows
            assertEquals(4, records.size());
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testMergeCSVFiles_EmptyFiles() throws IOException {
        File file1 = new File(tempDir, "empty1.csv");
        File file2 = new File(tempDir, "empty2.csv");
        File outputFile = new File(tempDir, "merged_empty.csv");

        // Creating empty files
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file1))) {
            writer.write("ID,Name,Age\n");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file2))) {
            writer.write("ID,Marks,Grade\n");
        }

        // Merge empty files
        mergeCSVFiles.mergeCSVFiles(file1.getAbsolutePath(), file2.getAbsolutePath(), outputFile.getAbsolutePath());

        // Read the merged file
        try (CSVReader csvReader = new CSVReader(new FileReader(outputFile))) {
            List<String[]> records = csvReader.readAll();

            // Validate that only header exists
            assertEquals(1, records.size());
            assertArrayEquals(new String[]{"ID", "Name", "Age", "Marks", "Grade"}, records.getFirst());
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        }
    }
}