package com.example.api.ofd.services;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.ofd.OfdRequestSpecification;
import com.example.api.ofd.models.merchant_ms.ProductsRequestFactory;
import com.example.api.ofd.models.merchant_ms.QRCodeRequest;

@Service
public class MerchantService {
    private static final String OFD_MERCHANT_BASE_PATH = "/v3/merchant";

    @Autowired
    private OfdRequestSpecification ofdReqSpec;

    public Response sendQRLinks(QRCodeRequest request) {
        return ofdReqSpec
                .getMerchantReqSpec()
                .basePath(OFD_MERCHANT_BASE_PATH)
                .body(request)
                .post("/qrcode");
    }

    public Response sendProductsInfo() {
        return ofdReqSpec
                .getMerchantReqSpec()
                .basePath(OFD_MERCHANT_BASE_PATH)
                .body(ProductsRequestFactory.createDefault())
                .post("/products");
    }
}
