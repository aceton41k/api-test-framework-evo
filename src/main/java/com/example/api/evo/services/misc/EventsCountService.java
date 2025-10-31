package com.example.api.evo.services.misc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import com.example.api.evo.models.misc.EventListResponse;
import com.example.api.evo.models.misc.NearbyUsersDataRequest;
import com.example.api.evo.models.misc.TransferBarcodeRequest;
import com.example.utils.DateTimeUtil;

import java.util.Map;

@Service
public class EventsCountService {
    @Autowired
    EvoApiRequestSpecification reqSpec;

    public <T> T getEventsCount(Class<T> responseType, Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("events.count")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(responseType);
    }

    public <T> T getTransferBarcode(
            TransferBarcodeRequest.Params params, Class<T> response, Map<String, String> headers) {
        var request = TransferBarcodeRequest.builder()
                .method("transfer.barcode")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(response);
    }

    public EventListResponse getEventList(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("event.list")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(EventListResponse.class);
    }

    public <T> T getNearbyUsers(NearbyUsersDataRequest.Params params, Class<T> responseType, Map<String, String> headers) {
        var request = NearbyUsersDataRequest.builder()
                .method("nearby.users")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(responseType);
    }
}
