package com.ioprogramming.day1.intermediate.filterrecords;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class FilterRecord {
    public void filter(String filepath){
        try(CSVReader csvReader = new CSVReader(new FileReader(filepath))){
            String[] nextLine;

            System.out.printf("%-5s %-10s %-5s %-5s%n", "ID", "Name", "Age", "Marks");

            // reading first line as they contain headers
            csvReader.readNext();

            while ((nextLine = csvReader.readNext()) != null){
                if(Integer.parseInt(nextLine[3].trim()) > 80){
                    System.out.printf("%-5s %-10s %-5s %-5s%n", nextLine[0], nextLine[1], nextLine[2], nextLine[3]);
                }
            }
        } catch (IOException | CsvValidationException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        String filepath = "E:\\Files\\Week5\\day1\\data.csv";

        FilterRecord filterRecord = new FilterRecord();
        filterRecord.filter(filepath);
    }
}
