package com.example.api.evo.models.transfer_p2p;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class IssuerListResponse extends BaseResponse<List<IssuerListResponse.Result>> {
    @Getter
    public static class Result {
        private String title;
        private String image;
        private List<BankItem> items;
    }

    @Getter
    public static class BankItem {
        private String image;

        @JsonProperty("bank_name")
        private String bankName;

        private List<DataItem> data;
    }

    @Getter
    public static class DataItem {
        private String key;
        private Long value;
    }
}
