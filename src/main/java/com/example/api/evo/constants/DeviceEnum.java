package com.example.api.evo.constants;

import lombok.Getter;

/**
 * Список устройств апи клиентов.
 */
@Getter
public enum DeviceEnum {
    ANDROID(1, "8.18.1"),
    IOS(2, "8.18.0"),
    WEB(3, "1.0");

    final int type;
    final String appVersion;

    DeviceEnum(int type, String version) {
        this.type = type;
        this.appVersion = version;
    }
}