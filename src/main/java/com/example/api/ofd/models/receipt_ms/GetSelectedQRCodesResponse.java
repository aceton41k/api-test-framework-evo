package com.example.api.ofd.models.receipt_ms;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetSelectedQRCodesResponse extends ResponseEntity {
    private List<ResponseBody> responseBody;

    @Getter
    @Builder
    public static class ResponseBody {
        private Long paymentId;
        private String qrCodeURL;
    }
}
