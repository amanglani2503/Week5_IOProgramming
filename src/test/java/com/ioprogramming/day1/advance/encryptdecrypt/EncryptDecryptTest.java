package com.ioprogramming.day1.advance.encryptdecrypt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncryptDecryptTest {

    @Test
    void testEncryptDecrypt() {
        String originalData = "HelloWorld";

        // Encrypt the data
        String encryptedData = EncryptDecrypt.encrypt(originalData);
        assertNotNull(encryptedData, "Encrypted data should not be null");

        // Decrypt the data
        String decryptedData = EncryptDecrypt.decrypt(encryptedData);
        assertNotNull(decryptedData, "Decrypted data should not be null");

        // Check if the original data is equal to decrypted data
        assertEquals(originalData, decryptedData, "Decrypted data should match original data");
    }
}
