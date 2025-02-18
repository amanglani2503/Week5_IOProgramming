package com.ioprogramming.day2.practiceproblems.filterrecords;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class JSONFilter {
    public static void main(String[] args) {
        try{
            String jsonData = "[\n" +
                    "    { \"name\": \"Alice Johnson\", \"email\": \"alice@example.com\", \"age\": 28, \"city\": \"New York\" },\n" +
                    "    { \"name\": \"Bob Smith\", \"email\": \"bob@example.com\", \"age\": 34, \"city\": \"Los Angeles\" },\n" +
                    "    { \"name\": \"Charlie Brown\", \"email\": \"charlie@example.com\", \"age\": 25, \"city\": \"Chicago\" }\n" +
                    "]";


            ObjectMapper objectMapper = new ObjectMapper();

            List<Person> persons = objectMapper.readValue(jsonData, new TypeReference<List<Person>>() {});

            List<Person> filteredPersons = new ArrayList<>();

            for(Person person : persons){
                if(person.getAge() > 25){
                    filteredPersons.add(person);
                }
            }

            String filteredJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(filteredPersons);

            System.out.println("Filtered Persons : ");
            System.out.println(filteredJson);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
