package com.ioprogramming.day1.advance.csvtoobjects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVToObjectsTest {

    private CSVToObjects csvToObjects;

    @TempDir
    File tempDir;

    @BeforeEach
    void setUp() {
        csvToObjects = new CSVToObjects();
    }

    @Test
    void testConvertCSVToObjects_ValidFile() throws IOException {
        File tempFile = new File(tempDir, "test.csv");

        // Writing test data to the temporary file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("ID,Name,Age,Marks\n"); // Header
            writer.write("1,John,20,80.5\n");
            writer.write("2,Alice,22,90.0\n");
            writer.write("3,Bob,21,75.0\n");
        }

        // Converting CSV to list of Student objects
        List<Student> students = csvToObjects.convertCSVToObjects(tempFile.getAbsolutePath());

        // Validating the conversion
        assertNotNull(students);
        assertEquals(3, students.size());

        // Checking values of the first student
        Student firstStudent = students.getFirst();
        assertEquals(1, firstStudent.getId());
        assertEquals("John", firstStudent.getName());
        assertEquals(20, firstStudent.getAge());
        assertEquals(80.5, firstStudent.getMarks(), 0.01);
    }

    @Test
    void testConvertCSVToObjects_EmptyFile() throws IOException {
        File tempFile = new File(tempDir, "empty.csv");

        // Writing an empty file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(""); // Empty content
        }

        // Converting CSV to list of Student objects
        List<Student> students = csvToObjects.convertCSVToObjects(tempFile.getAbsolutePath());

        // Validating that the list is empty
        assertNotNull(students);
        assertTrue(students.isEmpty());
    }

    @Test
    void testConvertCSVToObjects_InvalidFile() {
        File tempFile = new File(tempDir, "invalid.csv");

        // Writing some invalid content to simulate bad data
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("ID,Name,Age,Marks\n"); // Header
            writer.write("1,John,20,ABC\n"); // Invalid marks
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Converting CSV to list of Student objects
        List<Student> students = csvToObjects.convertCSVToObjects(tempFile.getAbsolutePath());

        // Validating that no students were parsed due to invalid data
        assertNotNull(students);
        assertTrue(students.isEmpty());
    }
}
