package com.example.utils;

import java.security.MessageDigest;

public class CryptoUtils {
    public static String md5(String input) {
        return hash(input, "MD5");
    }

    public static String sha256(String input) {
        return hash(input, "SHA-256");
    }
    public static String sha1(String input) {
        return hash(input, "SHA-1");
    }

    public static String sha512(String input) {
        return hash(input, "SHA-512");
    }

    private static String hash(String input, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] digested = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : digested) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
