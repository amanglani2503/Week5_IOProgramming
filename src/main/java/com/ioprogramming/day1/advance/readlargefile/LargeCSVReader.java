package com.ioprogramming.day1.advance.readlargefile;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class LargeCSVReader {

    public static void readLargeCSV(String filepath) {
        int batchSize = 100;
        int recordCount = 0;

        try (CSVReader csvReader = new CSVReader(new FileReader(filepath))) {
            String[] row;
            int currentBatchSize = 0;

            while ((row = csvReader.readNext()) != null) {
                recordCount++;
                currentBatchSize++;

                if (currentBatchSize == batchSize) {
                    System.out.println(Arrays.toString(row));
                    currentBatchSize = 0;
                }
            }

            System.out.println("Total records processed: " + recordCount);
        } catch (IOException | CsvValidationException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String filepath = "E:\\Files\\Week5\\day1\\largeFile.csv";
        readLargeCSV(filepath);
    }
}