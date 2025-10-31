package com.example.api.evo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterDeviceOperationResult {
    private String deviceId,
            registerToken,
            confirmToken,
            nextStep,
            imei;
}
