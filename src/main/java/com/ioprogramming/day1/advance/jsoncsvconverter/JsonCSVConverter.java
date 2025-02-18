package com.ioprogramming.day1.advance.jsoncsvconverter;

import java.io.*;
import java.util.*;

public class JsonCSVConverter {

    public void convertJsonToCsv(String jsonFilePath, String csvFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {

            writer.write("ID,Name,Age,Marks");
            writer.newLine();

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("{") && line.endsWith("}")) {
                    line = line.substring(1, line.length() - 1); // Remove curly braces
                    String[] keyValuePairs = line.split(",");  // Split by commas

                    String id = "", name = "", age = "", marks = "";

                    for (String pair : keyValuePairs) {
                        String[] keyValue = pair.split(":"); // Split by colon
                        String key = keyValue[0].trim().replace("\"", "");
                        String value = keyValue[1].trim().replace("\"", "");

                        if (key.equals("id")) id = value;
                        else if (key.equals("name")) name = value;
                        else if (key.equals("age")) age = value;
                        else if (key.equals("marks")) marks = value;
                    }

                    writer.write(id + "," + name + "," + age + "," + marks);
                    writer.newLine();
                }
            }

            System.out.println("JSON to CSV conversion completed.");
        } catch (IOException e) {
            System.out.println("Error while converting JSON to CSV: " + e.getMessage());
        }
    }

    public void convertCsvToJson(String csvFilePath, String jsonFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFilePath))) {

            String line;
            List<String[]> records = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                records.add(values);
            }


            writer.write("[");
            writer.newLine();

            for (int i = 1; i < records.size(); i++) {
                String[] record = records.get(i);
                String id = record[0];
                String name = record[1];
                String age = record[2];
                String marks = record[3];

                writer.write("{");
                writer.write("\"id\": \"" + id + "\", ");
                writer.write("\"name\": \"" + name + "\", ");
                writer.write("\"age\": \"" + age + "\", ");
                writer.write("\"marks\": \"" + marks + "\"");
                writer.write("}");

                if (i < records.size() - 1) {
                    writer.write(",");
                }

                writer.newLine();
            }

            writer.write("]");
            writer.newLine();

            System.out.println("CSV to JSON conversion completed.");
        } catch (IOException e) {
            System.out.println("Error while converting CSV to JSON: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String jsonFilePath = "E:\\Files\\Week5\\day1\\EmployeeData.json";
        String csvFilePath = "E:\\Files\\Week5\\day1\\EmployeeData.csv";
        String outputCSVFilePath = "E:\\Files\\Week5\\day1\\NewEmployeeData.csv";
        JsonCSVConverter converter = new JsonCSVConverter();

        converter.convertCsvToJson(csvFilePath, jsonFilePath);

        converter.convertJsonToCsv(jsonFilePath, outputCSVFilePath);
    }
}