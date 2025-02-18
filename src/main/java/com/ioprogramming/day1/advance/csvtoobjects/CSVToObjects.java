package com.ioprogramming.day1.advance.csvtoobjects;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVToObjects {

    public List<Student> convertCSVToObjects(String filepath) {
        List<Student> students = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(filepath))) {

            List<String[]> records = csvReader.readAll();

            if(!records.isEmpty()){
                records.removeFirst();

                for (String[] record : records) {
                    int id = Integer.parseInt(record[0]);
                    String name = record[1];
                    int age = Integer.parseInt(record[2]);
                    double marks = Double.parseDouble(record[3]);

                    Student student = new Student(id, name, age, marks);
                    students.add(student);
                }
            }
        } catch (IOException | NumberFormatException | CsvException e) {
            System.out.println(e.getMessage());
        }

        return students;
    }

    public static void main(String[] args) {
        String filepath = "E:\\Files\\Week5\\day1\\data.csv";

        CSVToObjects csvToObjects = new CSVToObjects();
        List<Student> studentList = csvToObjects.convertCSVToObjects(filepath);

        for(Student student : studentList){
            System.out.println(student);
        }
    }
}
