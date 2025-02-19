package com.ioprogramming.day2.handsonpracticeproblems.csvtojson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CsvToJson {
    public static void main(String[] args) {
        try {
            String csvFilePath = "E:\\Files\\Week5\\day2\\data.csv";

            CSVReader csvReader = new CSVReader(new FileReader(csvFilePath));
            List<String[]> records = csvReader.readAll();
            csvReader.close();

            if (records.isEmpty()) {
                System.out.println("CSV file is empty.");
                return;
            }

            String[] headers = records.getFirst();

            ObjectMapper objectMapper = new ObjectMapper();
            ArrayNode jsonArray = objectMapper.createArrayNode();

            for (int i = 1; i < records.size(); i++) { // Start from 1 to skip headers
                String[] row = records.get(i);
                ObjectNode jsonObject = objectMapper.createObjectNode();

                for (int j = 0; j < headers.length; j++) {
                    jsonObject.put(headers[j], row[j]);
                }

                jsonArray.add(jsonObject);
            }

            // Convert JSON node to String
            String jsonOutput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonArray);

            System.out.println("JSON Output:");
            System.out.println(jsonOutput);

        } catch (IOException | CsvException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
