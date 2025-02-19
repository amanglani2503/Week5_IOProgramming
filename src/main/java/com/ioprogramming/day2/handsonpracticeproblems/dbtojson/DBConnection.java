package com.ioprogramming.day2.handsonpracticeproblems.dbtojson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnection  {
    private static final String URL = "jdbc:mysql://localhost:3306/employeedb"; // Database name: Employee
    private static final String USER = "root";
    private static final String PASSWORD = "Asdfghjkl8871@"; // Change your password
    private static final String QUERY = "SELECT employee_id, name, department, salary FROM employees"; // Table: employee

    public List<Employee> fetchEmployees() {
        List<Employee> employees = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY)) {

            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getDouble("salary")
                );
                employees.add(employee);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employees;
    }
}