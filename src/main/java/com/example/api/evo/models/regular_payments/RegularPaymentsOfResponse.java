package com.example.api.evo.models.regular_payments;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class RegularPaymentsOfResponse extends BaseResponse<List<List<RegularPaymentsOfResponse.Result>>> {
    @Getter
    public static class Result {
        private Integer error;

        @JsonProperty("error_note")
        private String errorNote;
    }
}
