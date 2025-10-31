package com.example.api.merchant.services.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.merchant.MerchantApiRequestSpecification;
import com.example.api.merchant.models.invoice.GetInvoiceStatusResponse;

@Service
public class GetInvoiceStatusService {
    @Autowired
    MerchantApiRequestSpecification reqSpec;

    public GetInvoiceStatusResponse getInvoiceStatus(Object serviceId, Object invoiceId, String authToken) {
        return reqSpec.getRequestSpecification()
            .headers("Auth", authToken)
            .basePath("/invoice/status/{serviceId}/{invoiceId}")
            .pathParams("serviceId", serviceId, "invoiceId", invoiceId)
            .get()
            .as(GetInvoiceStatusResponse.class);
    }
    public GetInvoiceStatusResponse getInvoiceStatusWithOutAuthToken(Object serviceId, Object invoiceId) {
        return reqSpec.getRequestSpecification()
            .basePath("/invoice/status/{serviceId}/{invoiceId}")
            .pathParams("serviceId", serviceId, "invoiceId", invoiceId)
            .get()
            .as(GetInvoiceStatusResponse.class);
    }

}
