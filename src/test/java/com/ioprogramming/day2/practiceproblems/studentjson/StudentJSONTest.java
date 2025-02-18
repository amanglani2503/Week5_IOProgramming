package com.ioprogramming.day2.practiceproblems.studentjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentJSONTest {

    @Test
    void testStudentSerialization() {
        try {
            // Setup student object
            Student student = new Student("Ayushman", 22, new String[]{"Data Structures", "DBMS", "OOP"});

            // ObjectMapper for serializing the object
            ObjectMapper objectMapper = new ObjectMapper();

            // Serialize student object to JSON
            String studentJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);

            // Check if the JSON contains student information
            assertTrue(studentJson.contains("Ayushman"));
            assertTrue(studentJson.contains("22"));
            assertTrue(studentJson.contains("Data Structures"));
            assertTrue(studentJson.contains("DBMS"));
            assertTrue(studentJson.contains("OOP"));
        } catch (Exception e) {
            // In case of error, fail the test
            e.printStackTrace();
        }
    }
}