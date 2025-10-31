package com.example.api.merchant.services.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.merchant.MerchantApiRequestSpecification;
import com.example.api.merchant.models.invoice.CreateInvoiceInvalidRequest;
import com.example.api.merchant.models.invoice.CreateInvoiceRequest;
import com.example.api.merchant.models.invoice.CreateInvoiceResponse;

@Service
public class MerchantInvoiceService {
    @Autowired
    MerchantApiRequestSpecification reqSpec;

    public CreateInvoiceResponse createInvoice(CreateInvoiceRequest request, String authToken) {
        return reqSpec.getRequestSpecification()
            .headers("Auth", authToken)
            .basePath("/invoice/create")
            .body(request)
            .post()
            .as(CreateInvoiceResponse.class);
    }

    public CreateInvoiceResponse createInvoice(CreateInvoiceInvalidRequest request, String authToken) {
        return reqSpec.getRequestSpecification()
            .headers("Auth", authToken)
            .basePath("/invoice/create")
            .body(request)
            .post()
            .as(CreateInvoiceResponse.class);
    }
}
