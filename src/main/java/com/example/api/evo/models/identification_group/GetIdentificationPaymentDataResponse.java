package com.example.api.evo.models.identification_group;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.evo.models.BaseResponse;

import java.math.BigDecimal;
import java.util.List;

public class GetIdentificationPaymentDataResponse extends BaseResponse<GetIdentificationPaymentDataResponse.Result> {

    @Getter
    public static class Result {

        private String type;

        private Parameters parameters;

        private Service service;
    }

    @Getter
    public static class Parameters {
        private Integer amount;
    }

    @Getter
    public static class Service {
        private Integer id;

        private String name;

        @JsonProperty("category_id")
        private Integer categoryId;

        private Integer status;

        private String image;

        @JsonProperty("min_limit")
        private Long minLimit;

        @JsonProperty("max_limit")
        private Integer maxLimit;

        @JsonProperty("commission_percent")
        private BigDecimal commissionPercent;

        @JsonProperty("card_types")
        private List<String> cardTypes;

        private Boolean maintenance;
        private Boolean direct_payment;
        private Boolean favorite_permission;

        @JsonProperty("myhome_permission")
        private Integer myHomePermission;

        @JsonProperty("cashback_label")
        private Integer cashbackLabel;

        private Integer label;

        @JsonProperty("qr_only")
        private Boolean qrOnly;

    }
}
