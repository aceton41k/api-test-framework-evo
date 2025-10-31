package com.example.api.ofd.models.merchant_ms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ProductsRequest {
    @JsonProperty("Items")
    private List<Item> items;

    @JsonProperty("PaymentId")
    private Long paymentId;

    @JsonProperty("ReceivedECash")
    private Long receivedECash;

    @JsonProperty("ReceivedCard")
    private Long receivedCard;

    @JsonProperty("ReceivedCash")
    private Long receivedCash;

    @Getter
    @Builder
    public static class Item {
        @JsonProperty("Name")
        private String name;

        @JsonProperty("Barcode")
        private String barcode;

        @JsonProperty("Labels")
        private List<String> labels;

        @JsonProperty("SPIC")
        private String spic;

        @JsonProperty("PackageCode")
        private String packageCode;

        @JsonProperty("GoodPrice")
        private Long goodPrice;

        @JsonProperty("Price")
        private int price;

        @JsonProperty("VAT")
        private Long vat;

        @JsonProperty("VATPercent")
        private int vatPercent;

        @JsonProperty("Amount")
        private int amount;

        @JsonProperty("Discount")
        private Integer discount;

        @JsonProperty("Other")
        private Integer other;

        @JsonProperty("CommissionInfo")
        private CommissionInfo commissionInfo;
    }

    @Getter
    @Builder
    public static class CommissionInfo {
        @JsonProperty("TIN")
        private String tin;

        @JsonProperty("PINFL")
        private String pinfl;
    }
}
