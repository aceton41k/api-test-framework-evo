package com.example.api.evo.services;

import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.registration.LoginRequest;
import com.example.api.evo.models.registration.LoginResponse;
import com.example.api.evo.models.registration.RegisterRequest;
import com.example.api.evo.models.registration.RegisterResponse;
import com.example.utils.DateTimeUtil;

import static com.example.utils.DateTimeUtil.getCurrentTimestamp;
import static com.example.utils.TokenGenerator.*;

@Service
public class AuthenticationService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    @Step("User Login")
    public LoginResponse login(String deviceId, String phone, String sms, String pin) {

        String dateTime = getCurrentTimestamp();
        String confirmToken = generateConfirmToken(deviceId, sms, phone);
        String authToken = generateAuthToken(deviceId, confirmToken, phone);
        String password = generatePassword(authToken, dateTime, pin);

        LoginRequest.Params params = LoginRequest.Params.builder()
                .phoneNumber(phone)
                .deviceId(deviceId)
                .password(password)
                .datetime(dateTime)
                .build();

        LoginRequest request = LoginRequest.builder()
                .method("login")
                .id(dateTime)
                .params(params)
                .build();

        return reqSpec
                .getRequestSpecification()
                .body(request)
                .post()
                .as(LoginResponse.class);
    }

    @Step("User register")
    public RegisterResponse register(RegisterRequest.Params params) {
        RegisterRequest request = RegisterRequest.builder()
                .method("user.register")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();

        return reqSpec.getRequestSpecification()
                .body(request)
                .post()
                .as(RegisterResponse.class);
    }
}
