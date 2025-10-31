package com.example.api.ofd.models.receipt_ms;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetSelectedQRCodesRequest {
    private List<Long> paymentIds;
}
