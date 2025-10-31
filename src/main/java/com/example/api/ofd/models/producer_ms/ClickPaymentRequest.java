package com.example.api.ofd.models.producer_ms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClickPaymentRequest {
    @JsonProperty("paydoc_id")
    @Builder.Default
    private Integer paydocId = 575;
    @Builder.Default
    private Integer refund = 0;
    @JsonProperty("service_id")
    @Builder.Default
    private Integer serviceId = 112;
    @JsonProperty("phone_number")
    @Builder.Default
    private String phoneNumber = "998949210897";
    @Builder.Default
    private Integer latitude = 0;
    @Builder.Default
    private Integer longitude = 0;
    @JsonProperty("main_price")
    @Builder.Default
    private Integer mainPrice = 100000;
    @Builder.Default
    @JsonProperty("commission_price")
    private Integer commissionPrice = 1000;
    @Builder.Default
    private Integer amount = 1000;
    @Builder.Default
    @JsonProperty("status_time")
    private String statusTime = "2025-09-11T09:29:30";
    @Builder.Default
    private String lang = "UZ";
    @Builder.Default
    @JsonProperty("cntrg_info_param1")
    private String cntrgInfoParam1 = "";
    @Builder.Default
    @JsonProperty("cntrg_info_param2")
    private String cntrgInfoParam2 = "";
    @Builder.Default
    @JsonProperty("cntrg_info_param3")
    private String cntrgInfoParam3 = "";
    @Builder.Default
    @JsonProperty("cntrg_info_param4")
    private String cntrgInfoParam4 = "";
    @Builder.Default
    @JsonProperty("cntrg_info_param5")
    private String cntrgInfoParam5 = "";
    @Builder.Default
    @JsonProperty("epos_terminal_id")
    private String eposTerminalId = "";
    @Builder.Default
    @JsonProperty("abs_type")
    private String absType = "";
    @JsonProperty("interface")
    @Builder.Default
    private String interfaceValue = "U";
    @Builder.Default
    private Integer cardtype = 2;
    @Builder.Default
    private String pptid = "25246763555";
    @Builder.Default
    @JsonProperty("instant_refund")
    private Integer instantRefund = 0;
}
