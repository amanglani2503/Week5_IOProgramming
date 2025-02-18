package com.ioprogramming.day1.advance.csvreport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseFetcher {

    public List<String[]> fetchEmployeeRecords(String dbUrl, String username, String password) {
        String query = "SELECT employee_id, name, department, salary FROM employees";  // Adjust your query
        List<String[]> records = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(dbUrl, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String[] record = {
                        String.valueOf(resultSet.getInt("employee_id")),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        String.valueOf(resultSet.getDouble("salary"))
                };
                records.add(record);
            }

        } catch (SQLException e) {
            System.out.println("Error while fetching data from database: " + e.getMessage());
        }

        return records;
    }
}
