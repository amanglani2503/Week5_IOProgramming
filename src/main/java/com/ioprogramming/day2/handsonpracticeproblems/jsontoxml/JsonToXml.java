package com.ioprogramming.day2.handsonpracticeproblems.jsontoxml;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;


public class JsonToXml {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(new File("E:\\Files\\Week5\\day2\\student.json"));

            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);

            System.out.println("XML Output:");
            System.out.println(xml);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}