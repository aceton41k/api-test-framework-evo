package com.example.api.evo.services.invoice;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import com.example.api.evo.models.ResponseWithOkResult;
import com.example.api.evo.models.invoice.InvoiceListResponse;
import com.example.api.evo.models.invoice.InvoiceRejectRequest;
import com.example.utils.DateTimeUtil;

@Service
public class InvoiceService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    public InvoiceListResponse getInvoiceList(Map<String, String> headers) {
        RequestWithoutParams request = RequestWithoutParams.builder()
                .method("invoice.list")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(InvoiceListResponse.class);
    }

    public ResponseWithOkResult rejectInvoiceByID(InvoiceRejectRequest.Params params, Map<String, String> headers) {
        InvoiceRejectRequest request = InvoiceRejectRequest.builder()
                .method("invoice.reject")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ResponseWithOkResult.class);
    }
}
