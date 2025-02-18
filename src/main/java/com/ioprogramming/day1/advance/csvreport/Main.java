package com.ioprogramming.day1.advance.csvreport;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Database credentials (adjust these as per your database setup)
        String dbUrl = "jdbc:mysql://localhost:3306/EmployeeDB";  // Change to your DB URL
        String username = "root";  // Change to your DB username
        String password = "Asdfghjkl8871@";  // Change to your DB password

        // Path to write the CSV file
        String filepath = "E:\\Files\\Week5\\day1\\EmployeeData.csv";  // Change to your desired file path

        // Fetch employee data from the database
        DatabaseFetcher databaseFetcher = new DatabaseFetcher();
        List<String[]> employeeRecords = databaseFetcher.fetchEmployeeRecords(dbUrl, username, password);

        // Write the data to a CSV file
        CSVWriterUtil csvWriterUtil = new CSVWriterUtil();
        csvWriterUtil.writeToCSV(employeeRecords, filepath);
    }
}