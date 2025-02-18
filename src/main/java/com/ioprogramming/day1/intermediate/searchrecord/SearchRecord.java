package com.ioprogramming.day1.intermediate.searchrecord;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class SearchRecord {
    public void search(String filepath, String name){
        try(CSVReader csvReader = new CSVReader(new FileReader(filepath))){
            String[] nextLine;

            // reading row having headers
            csvReader.readNext();
            System.out.printf("%-5s %-10s %-5s %-5s%n", "ID", "Name", "Age", "Marks");
            while ((nextLine = csvReader.readNext()) != null){
                if(nextLine[1].equalsIgnoreCase(name)){
                    System.out.printf("%-5s %-10s %-5s %-5s%n", nextLine[0], nextLine[1], nextLine[2], nextLine[3]);
                }
            }
        } catch (IOException | CsvValidationException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        String filepath = "E:\\Files\\Week5\\day1\\write.csv";
        String targetName = "Charlie";

        SearchRecord searchRecord = new SearchRecord();
        searchRecord.search(filepath, targetName);
    }
}
