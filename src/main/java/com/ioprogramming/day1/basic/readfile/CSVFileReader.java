package com.ioprogramming.day1.basic.readfile;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class CSVFileReader {
    public void readcsv(String filepath){
        try(CSVReader csvReader = new CSVReader(new FileReader(filepath))){
            String[] nextLine;

            System.out.printf("%-5s %-10s %-5s %-5s%n", "ID", "Name", "Age", "Marks");
            while ((nextLine = csvReader.readNext()) != null){
                System.out.printf("%-5s %-10s %-5s %-5s%n", nextLine[0], nextLine[1], nextLine[2], nextLine[3]);
            }

        } catch (IOException | CsvValidationException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        String filepath = "E:\\Files\\Week5\\day1\\data.csv";

        CSVFileReader reader = new CSVFileReader();
        reader.readcsv(filepath);
    }
}
