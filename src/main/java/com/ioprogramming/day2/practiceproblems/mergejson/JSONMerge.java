package com.ioprogramming.day2.practiceproblems.mergejson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JSONMerge {
    public static void main(String[] args) {
        try{
            Car car1 = new Car("Tesla", "Model S", 2023, 79999);
            Car car2 = new Car("Tesla", "Model X", 2024, 99999);

            ObjectMapper objectMapper = new ObjectMapper();

            String json1 = objectMapper.writeValueAsString(car1);
            String json2 = objectMapper.writeValueAsString(car2);

            ArrayNode mergedNode = objectMapper.createArrayNode();
            mergedNode.add(objectMapper.readTree(json1));
            mergedNode.add(objectMapper.readTree(json2));

            String mergedJSON = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mergedNode);

            System.out.println("Merged JSON :- ");
            System.out.println(mergedJSON);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
