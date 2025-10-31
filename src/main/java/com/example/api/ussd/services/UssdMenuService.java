package com.example.api.ussd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.ussd.UssdRequestSpecification;
import com.example.api.ussd.models.menu.MenuPostRequestResponse;

@Service
public class UssdMenuService {
    @Autowired
    private UssdRequestSpecification reqSpec;

    public MenuPostRequestResponse baseResponse(MenuPostRequestResponse requestResponse) {
        return reqSpec.getRequestSpecification()
                .body(requestResponse)
                .post("/api/v1/menu")
                .as(MenuPostRequestResponse.class);
    }
}
