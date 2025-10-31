package com.example.api.ofd.models.settings_ms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class RecipeResponse {
    private Integer id;
    private Integer serviceId;
    private Integer priority;
    private Integer partialReturnServiceId;
    private String partialReturnPaymentType;
    private String partialReturnPaydocIdParamName;
    private Boolean enabled;
    private List<Condition> conditions;
    private PaymentInfo paymentInfo;

    @Getter
    public static class Condition {
        private String sourceField;
        private String matching;
        private String sourceValue;
    }

    @Getter
    public static class PaymentInfo {
        @JsonProperty("Main")
        private PaymentDetail main;

        @JsonProperty("Commission")
        private PaymentDetail commission;

        @Getter
        public static class PaymentDetail {
            @JsonProperty("PayDocId")
            private Integer payDocId;

            @JsonProperty("ServiceId")
            private Integer serviceId;

            @JsonProperty("ServiceName")
            private String serviceName;

            @JsonProperty("ServiceBranchCode")
            private String serviceBranchCode;

            @JsonProperty("Status")
            private Integer status;

            @JsonProperty("UserLanguage")
            private String userLanguage;

            @JsonProperty("ClickInterface")
            private String clickInterface;

            @JsonProperty("Main")
            private Boolean main;

            @JsonProperty("PaymentType")
            private String paymentType;

            @JsonProperty("ReceiptInfoRequest")
            private ReceiptInfoRequest receiptInfoRequest;

            @Getter
            public static class ReceiptInfoRequest {
                @JsonProperty("ReceiptId")
                private Integer receiptId;

                @JsonProperty("ReceivedECash")
                private Integer receivedECash;

                @JsonProperty("ReceivedCash")
                private Integer receivedCash;

                @JsonProperty("ReceivedCard")
                private Integer receivedCard;

                @JsonProperty("Time")
                private String time;

                @JsonProperty("TotalVAT")
                private Integer totalVAT;

                @JsonProperty("IsRefund")
                private Integer isRefund;

                @JsonProperty("ReceiptType")
                private Integer receiptType;

                @JsonProperty("Items")
                private List<Item> items;

                @JsonProperty("Location")
                private Location location;

                @JsonProperty("TaxiInfo")
                private TaxiInfo taxiInfo;

                @JsonProperty("RefundInfo")
                private RefundInfo refundInfo;

                @JsonProperty("ExtraInfo")
                private ExtraInfo extraInfo;

                @JsonProperty("MerchantInfo")
                private MerchantInfo merchantInfo;

                @Getter
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
                    private Integer goodPrice;

                    @JsonProperty("Price")
                    private Integer price;

                    @JsonProperty("Amount")
                    private Integer amount;

                    @JsonProperty("VAT")
                    private Integer vat;

                    @JsonProperty("VATPercent")
                    private Double vatPercent;

                    @JsonProperty("Discount")
                    private Integer discount;

                    @JsonProperty("Other")
                    private Integer other;

                    @JsonProperty("CommissionInfo")
                    private CommissionInfo commissionInfo;

                    @Getter
                    public static class CommissionInfo {
                        @JsonProperty("TIN")
                        private String tin;

                        @JsonProperty("PINFL")
                        private String pinfl;
                    }
                }

                @Getter
                public static class Location {
                    @JsonProperty("Latitude")
                    private Double latitude;

                    @JsonProperty("Longitude")
                    private Double longitude;
                }

                @Getter
                public static class TaxiInfo {
                    @JsonProperty("TIN")
                    private String tin;

                    @JsonProperty("PINFL")
                    private String pinfl;

                    @JsonProperty("CarNumber")
                    private String carNumber;
                }

                @Getter
                public static class RefundInfo {
                    @JsonProperty("TerminalID")
                    private String terminalID;

                    @JsonProperty("ReceiptId")
                    private String receiptId;

                    @JsonProperty("DateTime")
                    private String dateTime;

                    @JsonProperty("FiscalSign")
                    private String fiscalSign;
                }

                @Getter
                public static class ExtraInfo {
                    @JsonProperty("PhoneNumber")
                    private String phoneNumber;

                    @JsonProperty("Other")
                    private String other;
                }

                @Getter
                public static class MerchantInfo {
                    @JsonProperty("TIN")
                    private String tin;

                    @JsonProperty("PINFL")
                    private String pinfl;

                    @JsonProperty("Name")
                    private String name;

                    @JsonProperty("ContractDate")
                    private String contractDate;

                    @JsonProperty("ContractNumber")
                    private String contractNumber;
                }
            }
        }
    }
}
