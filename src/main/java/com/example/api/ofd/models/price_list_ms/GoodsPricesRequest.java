package com.example.api.ofd.models.price_list_ms;

import lombok.Builder;

import java.util.LinkedHashMap;
import java.util.Map;

@Builder
public class GoodsPricesRequest {
    private Long id;
    private Integer serviceId;
    private String mxikCode;
    private Integer page;
    private Integer size;

    public Map<String, Object> toQueryParams() {
        Map<String, Object> qp = new LinkedHashMap<>();
        if (id != null)
            qp.put("id", id);
        if (serviceId != null)
            qp.put("serviceId", serviceId);
        if (mxikCode != null && !mxikCode.isBlank())
            qp.put("mxikCode", mxikCode);
        if (page != null)
            qp.put("page", page);
        if (size != null)
            qp.put("size", size);
        return qp;
    }
}
