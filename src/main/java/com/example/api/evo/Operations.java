package com.example.api.evo;

import com.example.api.evo.models.registration.*;
import com.example.evo.models.registration.*;
import io.qameta.allure.Step;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.api.evo.constants.UserData;
import com.example.api.evo.models.LoginOperationResult;
import com.example.api.evo.models.RegisterOperationResult;
import uz.click.evo_api.models.registration.*;
import com.example.api.evo.services.AuthenticationService;
import com.example.api.evo.services.DeviceService;
import com.example.utils.TokenGenerator;

import static com.example.api.evo.constants.UserData.*;

/**
 * Класс, объединяющий частые API вызовы для быстрого создания тестовых данных.
 */
@Slf4j
@Component
public class Operations {
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    DeviceService deviceService;

    /**
     * Register device and login
     */
    @Step("Register device and login")
    public LoginOperationResult login(String phone, String sms, String pin, String imei) {
        RegisterDeviceOperationResult result = registerDevice(phone, sms, imei);
        String deviceId = result.getDeviceId();

        LoginResponse loginResponse = authenticationService.login(deviceId, phone, sms, pin);
        @NonNull String webSession = loginResponse.getResult().getWebSession();
        @NonNull String sessionKey = loginResponse.getResult().getSessionKey();
        return new LoginOperationResult(deviceId, sessionKey, webSession);
    }

    public LoginOperationResult login(UserData.User user) {
        return login(user.getPhoneNumber(), user.getSmsCode(), user.getPinCode(), user.getPhoneNumber() + "000");
    }

    /**
     * Full registration of 3 methods: device.register.request, device.register.request, register
     *
     * @return a wrapping class {@link RegisterOperationResult} to obtain useful data;
     */
    public RegisterOperationResult userRegister(String phone, String sms, String pin, String imei) {
        RegisterDeviceOperationResult result = registerDevice(phone, sms, imei);
        String deviceId = result.getDeviceId();
        String registerToken = result.getRegisterToken();
        var regParams = RegisterRequest.Params.createDefault(phone, registerToken, deviceId, pin);
        RegisterResponse registerResponse = authenticationService.register(regParams);
        @NonNull String webSession = registerResponse.getResult().getWebSession();
        @NonNull String sessionKey = registerResponse.getResult().getSessionKey();
        return new RegisterOperationResult(deviceId, registerToken, sessionKey, webSession);
    }

    public RegisterOperationResult userRegister(UserData.User user, String imei) {
        return userRegister(user.getPhoneNumber(), user.getSmsCode(), user.getPinCode(), user.getPhoneNumber() + imei);
    }

    public RegisterOperationResult userRegister(String phoneNumber) {
        return userRegister(phoneNumber, DEFAULT_SMS, DEFAULT_PIN, phoneNumber + DEFAULT_IMEI);
    }

    @Step("Register device and confirm")
    public RegisterDeviceOperationResult registerDevice(String phone, String sms, String imei) {
        DeviceRegisterRequest.Params deviceRegisterParams = DeviceRegisterRequest.Params.builder()
                .phoneNumber(phone)
                .deviceInfo("Iphone " + imei)
                .deviceName("Iphone " + imei)
                .imei(imei)
                .build();
        DeviceRegisterResponse deviceRegisterResponse = deviceService.deviceRegister(deviceRegisterParams);
        @NonNull String deviceId = deviceRegisterResponse.getResult().getDeviceId();

        DeviceRegisterConfirmRequest.Params confirmParams = DeviceRegisterConfirmRequest.Params.builder()
                .phoneNumber(phone)
                .deviceId(deviceId)
                .confirmToken(TokenGenerator.generateConfirmToken(deviceId, sms, phone))
                .upgrade(true)
                .build();

        DeviceRegisterConfirmResponse confirmResponse = deviceService.confirmDevice(confirmParams);
        @NonNull String registerToken = confirmResponse.getResult().getRegisterToken();
        @NonNull String confirmToken = confirmParams.getConfirmToken();
        @NonNull String nextStep = confirmResponse.getResult().getNextStep();
        return new RegisterDeviceOperationResult(deviceId, registerToken, confirmToken, nextStep, imei);
    }
}