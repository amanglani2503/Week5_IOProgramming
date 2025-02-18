package com.ioprogramming.day1.advance.encryptdecrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptDecrypt {

    private static final String SECRET_KEY = "1234567890123456"; // 16-byte key for AES-128


    public static String encrypt(String data) {
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedData = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedData); // Encode encrypted data to string
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String decrypt(String encryptedData) {
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decodedData = Base64.getDecoder().decode(encryptedData); // Decode base64 string
            byte[] decryptedData = cipher.doFinal(decodedData);
            return new String(decryptedData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
