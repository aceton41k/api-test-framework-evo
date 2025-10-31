package com.example.api.evo.models.misc;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class TransferBarcodeResponse extends BaseResponse<TransferBarcodeResponse.Result> {
    @Getter
    public static class Result {
        private String data;

        @JsonProperty("download_url")
        private String downloadUrl;
    }
}
