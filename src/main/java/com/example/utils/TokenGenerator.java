package com.example.utils;

public class TokenGenerator {

    private static final String SECRET_KEY = "c31bc3674770";

    public static String generateXRateControlManagementKey(String dateTime) {
        String managementKey = CryptoUtils.sha256(dateTime + SECRET_KEY);
        return managementKey + ":" + dateTime;
    }

    public static String generateConfirmToken(String deviceID, String smsCode, String phoneNumber) {
        return CryptoUtils.sha256(deviceID + smsCode + phoneNumber);
    }

    public static String generateAuthToken(String deviceID, String confirmToken, String phoneNumber) {
        return CryptoUtils.sha512(deviceID + confirmToken + phoneNumber);
    }

    public static String generatePassword(String authToken, String dateTime, String pin) {
        String pinCode = CryptoUtils.md5(pin);
        return CryptoUtils.sha512(authToken + dateTime + pinCode);
    }

    public static String encodePin(String pin) {
        return CryptoUtils.md5(pin);
    }

    public static String getMerchantAuthToken(String merchantUserId, Long timeStamp, String merchantSecretKey) {
        return merchantUserId + ":" + CryptoUtils.sha1(timeStamp + merchantSecretKey) + ":" + timeStamp;
    }
}
