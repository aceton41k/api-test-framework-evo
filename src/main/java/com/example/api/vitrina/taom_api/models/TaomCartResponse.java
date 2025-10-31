package com.example.api.vitrina.taom_api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import java.util.List;

@Getter
public class TaomCartResponse {

    private String id;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("products_amount")
    private Integer productsAmount;

    @JsonProperty("total_sum")
    private Integer totalSum;

    @JsonProperty("total_sum_format")
    private String totalSumFormat;

    @JsonProperty("merchant_id")
    private String merchantId;

    @JsonProperty("service_id")
    private String serviceId;

    private String type;
    private UserInfo userInfo;
    private String address;
    private String lat;
    private String lng;

    @JsonProperty("delivery_date")
    private String deliveryDate;

    @JsonProperty("delivery_time")
    private String deliveryTime;

    private String entrance;
    private String floor;
    private String apt;

    @JsonProperty("door_code")
    private String doorCode;

    @JsonProperty("house_number")
    private String houseNumber;

    private String comment;

    @JsonProperty("click_paydoc_id")
    private String clickPaydocId;

    @JsonProperty("paid_status")
    private Boolean paidStatus;

    private String status;
    private List<Status> statuses;

    @JsonProperty("on_hold")
    private Boolean onHold;

    @JsonProperty("hold_expires")
    private String holdExpires;

    private List<Product> products;

    @Getter
    public static class UserInfo {

        @JsonProperty("client_id")
        private Integer clientId;

        private String name;
        private String surname;

        @JsonProperty("phone_number")
        private String phoneNumber;
    }

    @Getter
    public static class Status {
        private String status;

        @JsonProperty("created_at")
        private String createdAt;
    }

    @Getter
    public static class Product {

        @JsonProperty("_id")
        private String id;

        private String name;
        private String description;
        private Integer price;
        private String image;

        @JsonProperty("category_id")
        private String categoryId;

        @JsonProperty("service_id")
        private String serviceId;

        @JsonProperty("merchant_id")
        private String merchantId;

        @JsonProperty("ikpu_code")
        private String ikpuCode;

        @JsonProperty("package_code")
        private String packageCode;

        private Integer amount;

        @JsonProperty("total_sum")
        private Integer totalSum;
    }
}
