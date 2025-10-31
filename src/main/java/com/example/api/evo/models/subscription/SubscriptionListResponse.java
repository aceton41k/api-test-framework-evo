package com.example.api.evo.models.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.evo.models.BaseResponse;

import java.util.List;

public class SubscriptionListResponse extends BaseResponse<List<SubscriptionListResponse.Result>> {
    @Getter
    public static class Result {
        private String id;
        @JsonProperty("service_id")
        private Integer serviceId;
        private String logo;
        private String code;
        private String interval;
        @JsonProperty("trial_interval")
        private String trialInterval;
        private Integer amount;
        private Integer expires;
        @JsonProperty("is_subscribed")
        private Boolean isSubscribed;
        @JsonProperty("is_trial")
        private Boolean isTrial;
        private Boolean renewal;
        @JsonProperty("account_id")
        private Integer accountId;
        @JsonProperty("description_info")
        private List<DescriptionInfo> descriptionInfo;
        private List<Info> info;
        @JsonProperty("error_status")
        private String errorStatus;
        @JsonProperty("error_note")
        private String errorNote;
    }

    @Getter
    public static class DescriptionInfo {
        private String label;
        private String value;
    }

    @Getter
    public static class Info {
        private String label;
        private String value;
    }
}