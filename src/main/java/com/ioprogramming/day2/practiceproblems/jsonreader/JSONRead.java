package com.ioprogramming.day2.practiceproblems.jsonreader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class JSONRead {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode rootNode = objectMapper.readTree(new File("E:\\Files\\Week5\\day2\\Employee.json"));

            System.out.printf("%-20s %-30s%n", "Name", "Email");
            for (JsonNode person : rootNode) {
                String name = person.get("name").asText();
                String email = person.get("email").asText();

                System.out.printf("%-20s %-30s%n", name, email);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
