package com.example.api.evo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.example.api.evo.config.HeadersFactory;

import java.util.Map;

@Getter
@AllArgsConstructor
public class RegisterOperationResult {
    private String deviceId,
            registerToken,
            sessionKey,
            webSession;

    public Map<String, String> getEvoHeaders() {
        return HeadersFactory.builder()
                .sessionKey(sessionKey)
                .deviceId(deviceId)
                .build();
    }
}