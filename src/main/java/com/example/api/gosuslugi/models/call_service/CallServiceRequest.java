package com.example.api.gosuslugi.models.call_service;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Getter
@SuperBuilder
public class CallServiceRequest extends BaseCallServiceRequest {
    private Map<String, String> fields;
}
