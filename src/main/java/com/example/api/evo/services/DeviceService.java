package com.example.api.evo.services;

import io.qameta.allure.Step;
import io.restassured.http.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.registration.DeviceRegisterConfirmRequest;
import com.example.api.evo.models.registration.DeviceRegisterConfirmResponse;
import com.example.api.evo.models.registration.DeviceRegisterRequest;
import com.example.api.evo.models.registration.DeviceRegisterResponse;
import com.example.utils.DateTimeUtil;
import com.example.utils.TokenGenerator;

import static io.restassured.RestAssured.given;

@Service
public class DeviceService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    @Step("Device register")
    public DeviceRegisterResponse deviceRegister(DeviceRegisterRequest.Params params) {
        String timestamp = DateTimeUtil.getCurrentTimestamp();
        DeviceRegisterRequest request = DeviceRegisterRequest.builder()
                .method("device.register.request")
                .id(timestamp)
                .params(params)
                .build();

        Header header =
                new Header("x-rate-control-management", TokenGenerator.generateXRateControlManagementKey(timestamp));

        return given().spec(reqSpec.getRequestSpecification())
                .body(request)
                .header(header)
                .post()
                .as(DeviceRegisterResponse.class);
    }

    @Step("Device register confirm")
    public DeviceRegisterConfirmResponse confirmDevice(DeviceRegisterConfirmRequest.Params params) {
        String timestamp = DateTimeUtil.getCurrentTimestamp();
        DeviceRegisterConfirmRequest request = DeviceRegisterConfirmRequest.builder()
                .method("device.register.confirm")
                .id(timestamp)
                .params(params)
                .build();

        return given().spec(reqSpec.getRequestSpecification())
                .body(request)
                .post()
                .as(DeviceRegisterConfirmResponse.class);
    }

    @Step("Device register confirm")
    public DeviceRegisterConfirmResponse confirmDevice(String phone, String sms, String deviceId) {
        DeviceRegisterConfirmRequest.Params params = DeviceRegisterConfirmRequest.Params.builder()
                .phoneNumber(phone)
                .deviceId(deviceId)
                .confirmToken(TokenGenerator.generateConfirmToken(deviceId, sms, phone))
                .upgrade(true)
                .build();

        DeviceRegisterConfirmRequest request = DeviceRegisterConfirmRequest.builder()
                .method("device.register.confirm")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();

        return given().spec(reqSpec.getRequestSpecification())
                .body(request)
                .post()
                .as(DeviceRegisterConfirmResponse.class);
    }
}
