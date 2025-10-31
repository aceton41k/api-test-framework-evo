package com.example.api.ofd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.ofd.OfdRequestSpecification;
import com.example.api.ofd.models.price_list_ms.GoodsPricesRequest;

import java.util.Map;

@Service
public class OfdPriceListMsService {

    @Autowired
    OfdRequestSpecification ofdReqSpec;

    public <T> T getPriceList(GoodsPricesRequest request, Class<T> responseType) {
        return ofdReqSpec.getPriceListMsReqSpec().basePath("/api/goods-prices").queryParams(request.toQueryParams())
                         .get().as(responseType);
    }

    public <T> T getPriceListWithParams(Map<String, Object> request, Class<T> responseType) {
        return ofdReqSpec.getPriceListMsReqSpec().basePath("/api/goods-prices").queryParams(request).get()
                         .as(responseType);
    }

}
