package com.example.api.evo.services.tickets;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import com.example.api.evo.models.ResponseWithOkResult;
import com.example.api.evo.models.tickets.AddTicketRequest;
import com.example.api.evo.models.tickets.GetTicketTypesResponse;
import com.example.api.evo.models.tickets.PaymentCancellationTicketAddRequest;
import com.example.api.evo.models.tickets.PaymentCancellationTicketTypesResponse;
import com.example.utils.DateTimeUtil;

@Service
public class TicketService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    public GetTicketTypesResponse getTicketTypes() {
        var request = RequestWithoutParams.builder()
                .method("ticket.get.types")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification().body(request).post().as(GetTicketTypesResponse.class);
    }

    public ResponseWithOkResult addTicket(AddTicketRequest.Params params, Map<String, String> headers) {
        var request = AddTicketRequest.builder()
                .method("ticket.add")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ResponseWithOkResult.class);
    }

    public PaymentCancellationTicketTypesResponse getPaymentCancellationTicketTypes(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("ticket.cancel.types")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(PaymentCancellationTicketTypesResponse.class);
    }

    public ResponseWithOkResult addPaymentCancellationTicket(
            PaymentCancellationTicketAddRequest.Params params, Map<String, String> headers) {
        var request = PaymentCancellationTicketAddRequest.builder()
                .method("ticket.cancel.add")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ResponseWithOkResult.class);
    }

    public ResponseWithOkResult requestCallbackTicket(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("ticket.callback.request")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ResponseWithOkResult.class);
    }
}
