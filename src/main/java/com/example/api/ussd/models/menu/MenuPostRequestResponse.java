package com.example.api.ussd.models.menu;


import lombok.*;

import java.util.Map;

@Builder @Getter @Setter
public class MenuPostRequestResponse {
        private Map<String, Object> headers;
        private String body;
        private Map<String, Object> optionalParameters;
        private String phoneNumber;
        private String phoneNumberStr;

}
