package com.example.api.ofd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.ofd.OfdRequestSpecification;
import com.example.api.ofd.models.receipt_ms.GetSelectedQRCodesRequest;
import com.example.api.ofd.models.receipt_ms.GetSelectedQRCodesResponse;
import com.example.api.ofd.models.receipt_ms.GetSelectedReceiptInfoRequest;
import com.example.api.ofd.models.receipt_ms.GetSelectedReceiptInfoResponse;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


@Service
public class ReceiptService {
    private static final String OFD_RECEIPT_BASE_PATH = "/v2/payment";

    @Autowired
    private OfdRequestSpecification ofdReqSpec;

    public GetSelectedQRCodesResponse getSelectedQRCodes(GetSelectedQRCodesRequest request) {
        return ofdReqSpec.getReceiptReqSpec()
                .basePath(OFD_RECEIPT_BASE_PATH)
                .body(request)
                .post("/getSelectedQRCodes")
                .as(GetSelectedQRCodesResponse.class);
    }

    public GetSelectedReceiptInfoResponse getSelectedReceiptInfo(GetSelectedReceiptInfoRequest request) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("paymentId", request.getPaymentId());
        queryParams.put("operationTime", request.getOperationTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS")));

        return ofdReqSpec.getReceiptReqSpec()
                .basePath(OFD_RECEIPT_BASE_PATH)
                .queryParams(queryParams)
                .get("/getSelectedReceiptInfo")
                .as(GetSelectedReceiptInfoResponse.class);
    }
}
