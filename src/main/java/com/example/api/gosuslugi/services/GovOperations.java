package com.example.api.gosuslugi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.api.evo.Operations;
import com.example.api.evo.constants.UserData;
import com.example.api.evo.models.LoginOperationResult;
import com.example.api.gosuslugi.models.login.GovApiLoginRequest;
import com.example.api.gosuslugi.models.login.GovApiLoginResponse;

@Component
public class GovOperations {
    @Autowired
    Operations operations;

    @Autowired
    GovApiAuthService govApiAuthService;

    public GovApiLoginResponse govLogin(UserData.User user) {
        LoginOperationResult loginResult = operations.login(user.getPhoneNumber(),
                user.getSmsCode(),
                user.getPinCode(),
                user.getPhoneNumber() + "000");
        String webSession = loginResult.getWebSession();
        var params = GovApiLoginRequest.builder()
                .webSession(webSession)
                .activate("1")
                .build();
        GovApiLoginResponse response = govApiAuthService.login(params, webSession);

        response.setWebSession(webSession);
        return response;
    }
}
