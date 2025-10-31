package com.example.api.ofd.models.receipt_ms;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseEntity {
    private String errorMessage;
    private Integer statusCode;
    private LocalDateTime dateTime;
}
