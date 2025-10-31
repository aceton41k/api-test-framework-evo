package com.example.api.evo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.example.api.evo.config.HeadersFactory;

import java.util.Map;

@Getter
@AllArgsConstructor
public class LoginOperationResult {
    private String deviceId,
            sessionKey,
            webSession;

    public Map<String, String> getEvoHeaders() {
        return HeadersFactory.builder()
                .sessionKey(sessionKey)
                .deviceId(deviceId)
                .build();
    }
}
