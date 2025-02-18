package com.ioprogramming.day1.advance.mergecsvfiles;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MergeCSVFiles {

    public void mergeCSVFiles(String file1, String file2, String outputFile) {
        Map<String, String[]> studentData = new HashMap<>();

        try (CSVReader reader1 = new CSVReader(new FileReader(file1))) {
            List<String[]> records1 = reader1.readAll();
            records1.removeFirst(); // Remove header

            // Store students1.csv data in a HashMap
            for (String[] record : records1) {
                String id = record[0].trim();
                studentData.put(id, new String[]{record[1].trim(), record[2].trim()});
            }
        } catch (IOException | CsvException e) {
            System.out.println("Error reading file1: " + e.getMessage());
        }

        try (CSVReader reader2 = new CSVReader(new FileReader(file2));
             CSVWriter writer = new CSVWriter(new FileWriter(outputFile))) {

            List<String[]> records2 = reader2.readAll();
            records2.removeFirst(); // Remove header

            // Write merged header
            writer.writeNext(new String[]{"ID", "Name", "Age", "Marks", "Grade"});

            // Merge data and write to the output file
            for (String[] record : records2) {
                String id = record[0].trim();
                String marks = record[1].trim();
                String grade = record[2].trim();

                if (studentData.containsKey(id)) {
                    String[] studentInfo = studentData.get(id);
                    writer.writeNext(new String[]{id, studentInfo[0], studentInfo[1], marks, grade});
                } else {
                    // If ID is not present in students1.csv, write with empty values for Name & Age
                    writer.writeNext(new String[]{id, "", "", marks, grade});
                }
            }

            // Add remaining records from students1.csv that are missing in students2.csv
            for (Map.Entry<String, String[]> entry : studentData.entrySet()) {
                if (!records2.stream().anyMatch(r -> r[0].trim().equals(entry.getKey()))) {
                    writer.writeNext(new String[]{entry.getKey(), entry.getValue()[0], entry.getValue()[1], "", ""});
                }
            }

        } catch (IOException | CsvException e) {
            System.out.println("Error merging files: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String file1 = "E:\\Files\\Week5\\day1\\students1.csv";
        String file2 = "E:\\Files\\Week5\\day1\\students2.csv";
        String outputFile = "E:\\Files\\Week5\\day1\\merged_students.csv";

        MergeCSVFiles merger = new MergeCSVFiles();
        merger.mergeCSVFiles(file1, file2, outputFile);

        System.out.println("CSV files merged successfully into " + outputFile);
    }
}