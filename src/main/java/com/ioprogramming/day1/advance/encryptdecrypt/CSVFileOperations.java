package com.ioprogramming.day1.advance.encryptdecrypt;

import java.io.*;

public class CSVFileOperations {

    // Function to write data to CSV file with encryption for sensitive fields
    public static void writeCsvWithEncryption(String filepath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {

            writer.write("ID,Name,Email,Salary");
            writer.newLine();

            String[] records = {
                    "1,Alice,alice@example.com,50000",
                    "2,Bob,bob@example.com,60000",
                    "3,Charlie,charlie@example.com,70000"
            };

            for (String record : records) {
                String[] fields = record.split(",");
                String id = fields[0];
                String name = fields[1];
                String email = EncryptDecrypt.encrypt(fields[2]);  // Encrypt email
                String salary = EncryptDecrypt.encrypt(fields[3]); // Encrypt salary


                writer.write(id + "," + name + "," + email + "," + salary);
                writer.newLine();
            }

            System.out.println("Data written to CSV with encryption.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void readCsvWithDecryption(String filepath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {

                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] fields = line.split(",");
                String id = fields[0];
                String name = fields[1];
                String email = EncryptDecrypt.decrypt(fields[2]);
                String salary = EncryptDecrypt.decrypt(fields[3]);

                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email + ", Salary: " + salary);
            }

            System.out.println("Data read from CSV with decryption.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        String filepath = "E:\\Files\\Week5\\day1\\encryptedData.csv";

        writeCsvWithEncryption(filepath);

        readCsvWithDecryption(filepath);
    }
}
