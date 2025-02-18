package com.ioprogramming.day1.advance.encryptdecrypt;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class CSVFileOperationsTest {

    private static final String FILE_PATH = "E:\\Files\\Week5\\day1\\testEncryptedData.csv";

    @BeforeAll
    static void setup() {
        // Ensure the file is cleared before testing (if needed)
        File file = new File(FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testWriteReadCsvWithEncryptionDecryption() {
        // Write data to CSV file with encryption
        CSVFileOperations.writeCsvWithEncryption(FILE_PATH);

        // Ensure the file was created and has data
        File file = new File(FILE_PATH);
        assertTrue(file.exists(), "CSV file should be created.");
        assertTrue(file.length() > 0, "CSV file should not be empty.");

        // Read data from CSV file with decryption and verify the data
        // (In a real test, you would capture the console output or refactor to return the data)
        // For simplicity, just calling read method to ensure no exceptions are thrown
        CSVFileOperations.readCsvWithDecryption(FILE_PATH);
    }
}
