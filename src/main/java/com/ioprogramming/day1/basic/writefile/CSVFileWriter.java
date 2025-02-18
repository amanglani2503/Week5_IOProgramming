package com.ioprogramming.day1.basic.writefile;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public class CSVFileWriter {
    public void writeCSV(String filepath){
        try(CSVWriter csvWriter = new CSVWriter(new FileWriter(filepath))){
            String[] header = { "ID", "Name", "Department", "Salary" };
            String[][] data = {
                    { "1", "Alice", "HR", "50000" },
                    { "2", "Bob", "IT", "70000" },
                    { "3", "Charlie", "Finance", "65000" },
                    { "4", "David", "Marketing", "60000" },
                    { "5", "Emma", "Sales", "55000" }
            };

            csvWriter.writeNext(header);

            for (String[] datum : data) {
                csvWriter.writeNext(datum);
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        String filepath = "E:\\Files\\Week5\\day1\\write.csv";

        CSVFileWriter csvFileWriter = new CSVFileWriter();
        csvFileWriter.writeCSV(filepath);
    }
}