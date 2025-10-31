package com.example.api.vitrina.vitrina_api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class VitrinaServiceResponse {

    @JsonProperty("has_menu")
    private Boolean hasMenu;
    private String id;
    private String logo;
    private String background;

    @JsonProperty("preview_text")
    private String previewText;
    private String name;

    @JsonProperty("click_service_name")
    private String clickServiceName;

    @JsonProperty("merchant_id")
    private String merchantId;

    @JsonProperty("merchant_user_id")
    private String merchantUserId;

    @JsonProperty("service_id")
    private String serviceId;

    @JsonProperty("tg_channel_id")
    private String tgChannelId;

    @JsonProperty("ikpu_code")
    private String ikpuCode;

    @JsonProperty("package_code")
    private String packageCode;

    private String nds;

    @JsonProperty("fiskal_type")
    private String fiskalType;

    private String latitude;
    private String longitude;
    private String address;

    @JsonProperty("region_id")
    private Integer regionId;

    @JsonProperty("category_id")
    private String categoryId;

    @JsonProperty("delivery_enabled")
    private Boolean deliveryEnabled;

    @JsonProperty("pickup_enabled")
    private Boolean pickupEnabled;

    @JsonProperty("delivery_info")
    private DeliveryInfo deliveryInfo;

    @JsonProperty("pickup_info")
    private PickupInfo pickupInfo;

    @JsonProperty("cooking_time")
    private String cookingTime;

    private String schedule;
    private String template;

    @JsonProperty("admin_phone")
    private String adminPhone;

    @JsonProperty("admin_name")
    private String adminName;

    @JsonProperty("support_phone")
    private String supportPhone;

    private Boolean status;

    @JsonProperty("platform_app_id")
    private String platformAppId;

    @JsonProperty("platform_sent")
    private Boolean platformSent;

    @JsonProperty("platform_is_moderated")
    private Boolean platformIsModerated;

    @JsonProperty("app_template")
    private String appTemplate;

    @JsonProperty("is_tested")
    private Boolean isTested;

    @JsonProperty("is_moderated")
    private Boolean isModerated;

    @JsonProperty("schedule_days")
    private ScheduleDays scheduleDays;

    @JsonProperty("is_closed")
    private Boolean isClosed;

    @JsonProperty("offer_ru")
    private String offerRu;

    @JsonProperty("offer_uz")
    private String offerUz;

    @JsonProperty("offer_en")
    private String offerEn;

    private Merchant merchant;

    @Getter
    public static class DeliveryInfo {
        private String from;
        private String to;

        @JsonProperty("offset_time")
        private String offsetTime;

        private String interval;
    }

    @Getter
    public static class PickupInfo {
        private String from;
        private String to;
    }

    @Getter
    public static class ScheduleDays {
        private List<Day> days;
        private Time time;

        @JsonProperty("cooking_time")
        private String cookingTime;
    }

    @Getter
    public static class Day {
        private String name;

        @JsonProperty("is_available")
        private Boolean isAvailable;
    }

    @Getter
    public static class Time {
        private String from;
        private String to;

        @JsonProperty("full_time")
        private Boolean fullTime;
    }

    @Getter
    public static class Merchant {
        @JsonProperty("_id")
        private String id;

        @JsonProperty("merchant_id")
        private String merchantId;

        @JsonProperty("merchant_user_id")
        private String merchantUserId;

        private String logo;

        @JsonProperty("api_token")
        private String apiToken;

        private String name;
    }

}
