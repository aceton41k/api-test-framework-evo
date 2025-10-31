package com.example.api.ofd.models.settings_ms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class RecipeRequest {
    private Integer serviceId;
    private Integer priority;
    private Integer partialReturnServiceId;
    private String partialReturnPaymentType;
    private String partialReturnPaydocIdParamName;
    private Boolean enabled;
    private List<Condition> conditions;
    private PaymentInfo paymentInfo;

    @Getter
    @Builder
    public static class Condition {
        private String sourceField;
        private String matching;
        private String sourceValue;
    }

    @Getter
    @Builder
    public static class PaymentInfo {
        private PaymentDetail main;
        private PaymentDetail commission;

        @Getter
        @Builder
        public static class PaymentDetail {
            private Integer payDocId;
            private Integer serviceId;

            @JsonProperty(required = true)
            private String serviceName;

            private String serviceBranchCode;
            private Integer status;
            private String userLanguage;
            private String clickInterface;
            private Boolean main;

            @JsonProperty(required = true)
            private String paymentType;

            private ReceiptInfoRequest receiptInfoRequest;

            @Getter
            @Builder
            public static class ReceiptInfoRequest {
                private Integer receiptId;
                private Integer receivedECash;
                private Integer receivedCash;
                private Integer receivedCard;
                private String time;
                private Integer totalVAT;
                private Integer isRefund;

                @JsonProperty(required = true)
                private Integer receiptType;

                private List<Item> items;
                private Location location;
                private TaxiInfo taxiInfo;
                private RefundInfo refundInfo;
                private ExtraInfo extraInfo;
                private MerchantInfo merchantInfo;

                @Getter
                @Builder
                public static class Item {
                    private String name;
                    private String barcode;
                    private List<String> labels;
                    private String spic;
                    private String packageCode;
                    private Integer goodPrice;
                    private Integer price;
                    private Integer amount;
                    private Integer vat;
                    private Integer vatPercent;
                    private Integer discount;
                    private Integer other;
                    private CommissionInfo commissionInfo;

                    @Getter
                    @Builder
                    public static class CommissionInfo {
                        private String tin;
                        private String pinfl;
                    }
                }

                @Getter
                @Builder
                public static class Location {
                    private Double latitude;
                    private Double longitude;
                }

                @Getter
                @Builder
                public static class TaxiInfo {
                    private String tin;
                    private String pinfl;
                    private String carNumber;
                }

                @Getter
                @Builder
                public static class RefundInfo {
                    private String terminalID;
                    private String receiptId;
                    private String dateTime;
                    private String fiscalSign;
                }

                @Getter
                @Builder
                public static class ExtraInfo {
                    @JsonProperty(required = true)
                    private String phoneNumber;

                    private String other;
                }

                @Getter
                @Builder
                public static class MerchantInfo {
                    private String tin;
                    private String pinfl;
                    private String name;
                    private String contractDate;
                    private String contractNumber;
                }
            }
        }
    }
}
