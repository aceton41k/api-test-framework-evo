package com.example.api.travel.constants;

public class TravelMyAgentData {
    public static final String MY_AGENT_BASE_URI = "MY_AGENT_BASE_URI";

    public static String apiKey() {
        String key = System.getProperty("travel.myagent.apiKey");
        if (key == null || key.isBlank()) {
            key = System.getenv("TRAVEL_MYAGENT_APIKEY");
        }
        if (key == null || key.isBlank()) {
            throw new IllegalStateException("API key is not provided");
        }
        return key;
    }
}
