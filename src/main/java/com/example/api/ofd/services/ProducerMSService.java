package com.example.api.ofd.services;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.ofd.OfdRequestSpecification;
import com.example.api.ofd.models.producer_ms.ClickPaymentRefundRequest;
import com.example.api.ofd.models.producer_ms.ClickPaymentRequest;
import com.example.api.ofd.models.producer_ms.ProducerFiscallingStatusItemResponse;
import com.example.api.ofd.models.producer_ms.ProducerStatusItemResponse;

import java.util.List;


@Service
public class ProducerMSService {
    @Autowired
    OfdRequestSpecification ofdReqSpec;

    String fiscallingUrl = "/v1/click-payment";

    public Response fiscalingPaymentTicket(ClickPaymentRequest request) {
        return ofdReqSpec.getProducerMSReqSpec().basePath(fiscallingUrl).body(request).post();
    }

    public Response fiscalingRefundPaymentTicket(ClickPaymentRefundRequest request) {
        return ofdReqSpec.getProducerMSReqSpec().basePath(fiscallingUrl).body(request).post();
    }

    public List<ProducerStatusItemResponse> checkStatusByPaydocId(Integer paydocId) {
        return ofdReqSpec.getProducerMSReqSpec().basePath(fiscallingUrl + "/" + paydocId).get()
                         .as(new TypeRef<List<ProducerStatusItemResponse>>() {});
    }

    public ProducerFiscallingStatusItemResponse checkFiscallingStatusByPaydocId(Integer paydocId) {
        return ofdReqSpec.getSupportMSReqSpec().basePath("v2/receipt-data/by-paydoc_id")
                         .queryParams("paydoc_id", paydocId).get().as(ProducerFiscallingStatusItemResponse.class);
    }
}
