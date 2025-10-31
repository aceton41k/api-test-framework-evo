package com.example.api.avto;

import lombok.Getter;

@Getter
public class BaseModelResponse<T> {
    private Boolean error;
    private String message;
    private T data;
}
