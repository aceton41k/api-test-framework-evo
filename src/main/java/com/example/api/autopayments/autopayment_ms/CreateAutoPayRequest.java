package com.example.api.autopayments.autopayment_ms;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateAutoPayRequest {
    @Builder.Default
    private Integer serviceId = 5;
    @Builder.Default
    private Integer accountId = 12345678;
    @Builder.Default
    private Integer autopayType = 1;
    @Builder.Default
    private Parameters parameters = Parameters.builder().build();

    @Getter
    @Builder
    public static class Parameters {
        @Builder.Default
        private Integer type = 3;
        @Builder.Default
        private String title = "Оплата телефона";
        @Builder.Default
        private Integer amount = 1001;

        @Builder.Default
        private String cntrgInfoParam2 = "977734075";
        @Builder.Default
        private String cntrgInfoParam3 = "3";
        @Builder.Default
        private String cntrgInfoParam4 = "4";
        @Builder.Default
        private String cntrgInfoParam5 = "5";

        @Builder.Default
        private String startDate = "12.12.2025"; // dd.MM.yyyy
        @Builder.Default
        private Integer daysIntervalAmount = 40;
        @Builder.Default
        private String paytime = "19:00";     // HH:mm
        @Builder.Default
        private Integer weeklyDay = 5;
        @Builder.Default
        private Integer monthlyDay = 15;
        @Builder.Default
        private Integer step = 0;
    }
}
