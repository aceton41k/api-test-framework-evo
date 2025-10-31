package com.example.api.evo.models.tickets;

import java.util.List;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class PaymentCancellationTicketTypesResponse
        extends BaseResponse<PaymentCancellationTicketTypesResponse.Result> {
    @Getter
    public static class Result {
        private String title;
        private List<Option> options;
    }

    @Getter
    public static class Option {
        private String type;
        private String name;
    }
}
