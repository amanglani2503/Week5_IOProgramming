package com.ioprogramming.day1.basic.countrows;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class CountRowsInCSV {
    public int countRows(String filepath){
        int rowCount = 0;
        try(CSVReader csvReader = new CSVReader(new FileReader(filepath))){

            while (csvReader.readNext() != null){
                rowCount++;
            }
        } catch (IOException | CsvValidationException e){
            System.out.println(e.getMessage());
        }

        // excluding header row
        return --rowCount;
    }

    public static void main(String[] args) {
        String filepath = "E:\\Files\\Week5\\day1\\data.csv";

        CountRowsInCSV rowCounter = new CountRowsInCSV();
        int rows = rowCounter.countRows(filepath);

        System.out.println("Number of rows : " + rows);
    }
}
