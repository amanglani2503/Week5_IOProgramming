package com.ioprogramming.day1.advance.csvreport;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class CSVWriterUtilTest {

    @Test
    void testWriteToCSV() throws IOException {
        // Prepare test data
        List<String[]> records = Arrays.asList(
                new String[]{"1", "John", "HR", "5000"},
                new String[]{"2", "Alice", "Engineering", "6000"},
                new String[]{"3", "Bob", "Sales", "4500"}
        );

        String filepath = "test_employee_data.csv";

        // Write data to CSV
        CSVWriterUtil writerUtil = new CSVWriterUtil();
        writerUtil.writeToCSV(records, filepath);

        // Read the written file and verify content
        try (CSVReader csvReader = new CSVReader(new FileReader(filepath))) {
            String[] header = csvReader.readNext();  // Read header
            assertArrayEquals(new String[]{"Employee ID", "Name", "Department", "Salary"}, header);

            String[] firstRecord = csvReader.readNext();  // Read first record
            assertArrayEquals(new String[]{"1", "John", "HR", "5000"}, firstRecord);

            String[] secondRecord = csvReader.readNext();  // Read second record
            assertArrayEquals(new String[]{"2", "Alice", "Engineering", "6000"}, secondRecord);

            String[] thirdRecord = csvReader.readNext();  // Read third record
            assertArrayEquals(new String[]{"3", "Bob", "Sales", "4500"}, thirdRecord);
        } catch (CsvValidationException e) {
            System.out.println(e.getMessage());
        }
    }
}
