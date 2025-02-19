package com.ioprogramming.day2.handsonpracticeproblems.dbtojson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReportGenerator {
    public void generateJsonReport(List<Employee> employees, String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ArrayNode jsonArray = objectMapper.createArrayNode();

            for (Employee employee : employees) {
                ObjectNode jsonObject = objectMapper.createObjectNode();
                jsonObject.put("employee_id", employee.getEmployeeId());
                jsonObject.put("name", employee.getName());
                jsonObject.put("department", employee.getDepartment());
                jsonObject.put("salary", employee.getSalary());
                jsonArray.add(jsonObject);
            }

            // Convert JSON Array to String
            String jsonOutput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonArray);
            System.out.println("Generated JSON Report:");
            System.out.println(jsonOutput);

            // Save JSON to File
            objectMapper.writeValue(new File(filePath), jsonArray);
            System.out.println("JSON Report saved successfully at: " + filePath);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}