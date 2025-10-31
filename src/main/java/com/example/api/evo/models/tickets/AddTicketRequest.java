package com.example.api.evo.models.tickets;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class AddTicketRequest extends BaseRequest<AddTicketRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        private String type;

        @JsonProperty("client_name")
        private String clientName;

        private String description;
    }
}
