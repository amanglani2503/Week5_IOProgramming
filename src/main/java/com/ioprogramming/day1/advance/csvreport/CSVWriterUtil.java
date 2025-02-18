package com.ioprogramming.day1.advance.csvreport;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriterUtil {

    public void writeToCSV(List<String[]> records, String filepath) {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(filepath))) {
            // Writing the header to the CSV
            String[] header = {"Employee ID", "Name", "Department", "Salary"};
            csvWriter.writeNext(header);

            // Writing the data rows
            for (String[] record : records) {
                csvWriter.writeNext(record);
            }

            System.out.println("Employee records have been written to the CSV file.");

        } catch (IOException e) {
            System.out.println("Error while writing to CSV: " + e.getMessage());
        }
    }
}