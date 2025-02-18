package com.ioprogramming.day1.advance.detectduplicates;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DetectDuplicates {

    public static void detectDuplicates(String filepath) {
        Set<String> seenIDs = new HashSet<>();  // Set to store unique IDs
        try (CSVReader csvReader = new CSVReader(new FileReader(filepath))) {
            String[] row;
            boolean isFirstLine = true;  // Skip header
            while ((row = csvReader.readNext()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;  // Skip header line
                    continue;
                }

                String id = row[0];  // ID is the first column

                // If ID already exists in the set, it's a duplicate
                if (seenIDs.contains(id)) {
                    System.out.println("Duplicate record: " + String.join(",", row));
                } else {
                    seenIDs.add(id);  // Add ID to the set if not present
                }
            }
        } catch (IOException | CsvValidationException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String filepath = "E:\\Files\\Week5\\day1\\data.csv";  // Replace with your file path
        detectDuplicates(filepath);
    }
}