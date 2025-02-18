package com.ioprogramming.day2.practiceproblems.mergejson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JSONMergeTest {

    @Test
    void testJSONMerging() {
        try {
            Car car1 = new Car("Tesla", "Model S", 2023, 79999);
            Car car2 = new Car("Tesla", "Model X", 2024, 99999);

            ObjectMapper objectMapper = new ObjectMapper();

            String json1 = objectMapper.writeValueAsString(car1);
            String json2 = objectMapper.writeValueAsString(car2);


            ArrayNode mergedNode = objectMapper.createArrayNode();
            mergedNode.add(objectMapper.readTree(json1));
            mergedNode.add(objectMapper.readTree(json2));

            assertEquals(2, mergedNode.size());

            JsonNode firstCarNode = mergedNode.get(0);
            assertEquals("Tesla", firstCarNode.get("brand").asText());
            assertEquals("Model S", firstCarNode.get("model").asText());
            assertEquals(2023, firstCarNode.get("year").asInt());
            assertEquals(79999, firstCarNode.get("price").asInt());

            JsonNode secondCarNode = mergedNode.get(1);
            assertEquals("Tesla", secondCarNode.get("brand").asText());
            assertEquals("Model X", secondCarNode.get("model").asText());
            assertEquals(2024, secondCarNode.get("year").asInt());
            assertEquals(99999, secondCarNode.get("price").asInt());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
