package com.example.api.evo.models.auto_pay;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import com.example.api.evo.models.BaseRequest;
import com.example.utils.DateTimeUtil;

import static com.example.api.evo.constants.ServiceData.API_VERSION;

@Getter
@SuperBuilder
public class AutoPaySaveRequest extends BaseRequest<AutoPaySaveRequest.Params> {

    @Getter
    @Builder
    public static class Params {
        @JsonProperty("api_version")
        @Builder.Default
        private Integer apiVersion = API_VERSION;

        @JsonProperty("autopay_type")
        @Builder.Default
        private Integer autopayType = AutoPayType.SCHEDULE;

        @JsonProperty("autopay_id")
        private Long autopayId;

        @JsonProperty("service_id")
        private Long serviceId;

        @JsonProperty("account_id")
        private Long accountId;

        private Parameters parameters;
    }

    @Getter
    @Builder
    public static class Parameters {
        @JsonProperty("autopay_name")
        @Builder.Default
        private String autopayName = "AutoPay Test " + DateTimeUtil.getCurrentTimestamp();

        @JsonProperty("autopay_paytime")
        private String autopayPayTime;

        @JsonProperty("autopay_monthly_day")
        private Integer autopayMonthlyDay;

        @JsonProperty("autopay_weekly_day")
        private Integer autopayWeeklyDay;

        @JsonProperty("phone_num")
        private String phoneNum;

        private Float amount;

        @Builder.Default
        @JsonProperty("autopay_type")
        private Integer autopayType = AutoPayType.MONTHLY;
    }
}
