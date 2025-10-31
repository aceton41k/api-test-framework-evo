package com.example.api.travel.constants;

public class UnitTravelData {
    public static final String BASE_URI = "BASE_URI";

    public static String apiKey() {
        String key = System.getProperty("unit.travel.apiKey");
        if (key == null || key.isBlank()) {
            key = System.getenv("UNIT_TRAVEL_APIKEY");
        }
        if (key == null || key.isBlank()) {
            throw new IllegalStateException("API key is not provided");
        }
        return key;
    }
}
