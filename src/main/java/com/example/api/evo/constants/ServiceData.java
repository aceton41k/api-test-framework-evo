package com.example.api.evo.constants;

import lombok.Getter;

@Getter
public class ServiceData {
    public static final String JSON_RPC_VERSION = "2.0";
    public static final int API_VERSION = 7;
    public static final int DEVICE_TYPE_ANDROID = 1;
    public static final int DEVICE_TYPE_IOS = 2;
    public static final int NEWS_ID = 522;
    public static final int NEWS_ID_1 = 523;
    public static final long SERVICE_ID_UMS = 2;
    public static final int SERVICE_ID_INTERNET_PACKAGES = 4422;
    public static final int SERVICE_ID_BEE = 3;
    public static final int REGION_TASHKENT = 100;
    public static final int CITY_TASHKENT = 101;

    private ServiceData() {
        // Приватный конструктор, чтобы запретить создание экземпляров класса
    }
}
