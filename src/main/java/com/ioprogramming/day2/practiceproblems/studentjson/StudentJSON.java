package com.ioprogramming.day2.practiceproblems.studentjson;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StudentJSON {
    public static void main(String[] args) {
        try{
            Student student = new Student("Ayushman", 22, new String[]{"Data Structures", "DBMS", "OOP"});

            ObjectMapper objectMapper = new ObjectMapper();

            String studentJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);

            System.out.println("JSON Object for student : ");
            System.out.println(studentJson);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
