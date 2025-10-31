package com.example.api.evo.config;

import java.util.HashMap;
import java.util.Map;

public class HeadersFactory {
    private final Map<String, String> headers = new HashMap<>();

    private HeadersFactory() {
    }

    public static HeadersFactory builder() {
        return new HeadersFactory();
    }

    public HeadersFactory sessionKey(String sessionKey) {
        headers.put("session-key", sessionKey);
        return this;
    }

    public HeadersFactory webSession(String webSession) {
        headers.put("web-session", webSession);
        return this;
    }

    public HeadersFactory deviceId(String deviceId) {
        headers.put("device-id", deviceId);
        return this;
    }

    public HeadersFactory authBearerToken(String token) {
        headers.put("Authorization", "Bearer " + token);
        return this;
    }

    public Map<String, String> build() {
        return headers;
    }
}
