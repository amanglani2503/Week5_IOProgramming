package com.ioprogramming.day2.handsonpracticeproblems.readjson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;


public class ReadJSON {
    public static void printJson(JsonNode rootNode){
        Queue<Map.Entry<String, JsonNode>> queue = new LinkedList<>();

        if(rootNode.isObject()){
            Iterator<Map.Entry<String , JsonNode>> elements = rootNode.fields();
            while(elements.hasNext()){
                queue.add(elements.next());
            }
        }

        while (!queue.isEmpty()){
            Map.Entry<String, JsonNode> entry = queue.poll();
            String key = entry.getKey();
            JsonNode value = entry.getValue();

            if(value.isObject()) {
                System.out.println(key + " : {");
                Iterator<Map.Entry<String, JsonNode>> fields = value.fields();
                while (fields.hasNext()){
                    queue.add(fields.next());
                }
                System.out.println("}");
            } else if (value.isArray()) {
                System.out.println(key + " : [");
                for(JsonNode element : value){
                    if(element.isObject()) {
                        Iterator<Map.Entry<String, JsonNode>> fields = value.fields();
                        while (fields.hasNext()){
                            queue.add(fields.next());
                        }
                    } else {
                        System.out.println(" " + element.asText());
                    }
                }
                System.out.println("]");
            } else{
                System.out.println(key + " : " + value.asText());
            }
        }
    }

    public static void main(String[] args) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode rootNode = objectMapper.readTree(new File("E:\\Files\\Week5\\day2\\student.json"));

            printJson(rootNode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
