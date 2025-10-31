package com.example.api.ofd.models.receipt_ms;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class GetSelectedReceiptInfoRequest {
    private Long paymentId;
    private LocalDateTime operationTime;
}
