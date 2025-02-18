package com.ioprogramming.day1.advance.datavalidator;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern emailPattern = Pattern.compile(emailRegex);

    private static final String phoneRegex= "^[0-9]{10}$";
    private static final Pattern phonepattern = Pattern.compile(phoneRegex);

    public boolean isValidEmail(String email) {
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        Matcher matcher = phonepattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public void validateCSV(String filepath) {
        try (CSVReader csvReader = new CSVReader(new FileReader(filepath))) {
            List<String[]> records = csvReader.readAll();

            String[] header = records.getFirst();
            System.out.println(String.join(",", header));

            for (int i = 1; i < records.size(); i++) {
                String[] record = records.get(i);

                String email = record[3];
                String phoneNumber = record[4];

                if (!isValidEmail(email)) {
                    System.out.println("Invalid Email at row " + (i + 1) + ": " + email);
                }

                if (!isValidPhoneNumber(phoneNumber)) {
                    System.out.println("Invalid Phone Number at row " + (i + 1) + ": " + phoneNumber);
                }
            }

        } catch (IOException | CsvException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        String filepath = "E:\\Files\\Week5\\day1\\validator.csv"; // Path to the CSV file

        Validator validator = new Validator();
        validator.validateCSV(filepath);
    }
}
