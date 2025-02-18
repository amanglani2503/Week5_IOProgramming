package com.ioprogramming.day1.intermediate.modifyrecord;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ModifyRecord {
    public void modify(String inputFilePath, String outputFilePath){
        try(CSVReader csvReader = new CSVReader(new FileReader(inputFilePath));
            CSVWriter csvWriter = new CSVWriter(new FileWriter(outputFilePath))) {
            List<String[]> records = csvReader.readAll();

            String[] currentRecord;

            for(int i = 1 ; i < records.size() ; i++){
                currentRecord = records.get(i);

                if(currentRecord[2].equalsIgnoreCase("IT")){
                    try {
                        double salary = Double.parseDouble(currentRecord[3]);
                        double updatedSalary = salary * 1.10; // Increase by 10%
                        currentRecord[3] = String.valueOf(updatedSalary); // Update salary field
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid salary format for employee: " + currentRecord[1]);
                    }
                }
                csvWriter.writeNext(currentRecord);

            }
        } catch (IOException | CsvException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        String inputFilepath = "E:\\Files\\Week5\\day1\\write.csv";
        String outputFilepath = "E:\\Files\\Week5\\day1\\updatedWrite.csv";

        ModifyRecord modifyRecord = new ModifyRecord();
        modifyRecord.modify(inputFilepath, outputFilepath);
    }
}
