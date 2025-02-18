package com.ioprogramming.day1.intermediate.sortrecords;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SortRecords {
    public void sortAndStoreTop5(String inputFilePath, String outputFilePath) {
        try (CSVReader csvReader = new CSVReader(new FileReader(inputFilePath));
             CSVWriter csvWriter = new CSVWriter(new FileWriter(outputFilePath))) {

            List<String[]> records = csvReader.readAll();

            if (records.isEmpty() || records.size() == 1) {
                System.out.println("No data to process.");
                return;
            }

            String[] header = records.getFirst();
            records.subList(1, records.size()).sort((record1, record2) ->
                    Double.compare(Double.parseDouble(record2[3]), Double.parseDouble(record1[3])));

            csvWriter.writeNext(header);
            for (int i = 1; i <= Math.min(5, records.size() - 1); i++) {
                csvWriter.writeNext(records.get(i));
            }

            System.out.println("Sorted data written to " + outputFilePath); // Debug print
        } catch (IOException | CsvException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        String filepath = "E:\\Files\\Week5\\day1\\write.csv"; // Path to the CSV file
        String outputFilePath = "E:\\Files\\Week5\\day1\\sortedData.csv"; // Path to store sorted data

        SortRecords sortRecords = new SortRecords();
        sortRecords.sortAndStoreTop5(filepath, outputFilePath);
    }
}
