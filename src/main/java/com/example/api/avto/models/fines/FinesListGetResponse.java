package com.example.api.avto.models.fines;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.avto.BaseModelResponse;

import java.util.List;

@Getter
public class FinesListGetResponse extends BaseModelResponse<List<FinesListGetResponse.ViolationData>> {

    @Getter
    public static class ViolationData {

        private Integer id;

        @JsonProperty("doc_id")
        private Long docId;

        private String date;

        @JsonProperty("qaror_date")
        private String qarorDate;

        @JsonProperty("amount_to_pay")
        private Integer amountToPay;

        private Integer privil;

        private String url;

        private Double lat;

        private Double lng;

        private Violation violation;

        private Object status;

        @JsonProperty("sery_number")
        private String seryNumber;

        private Long amount;

        private String place;

        @JsonProperty("plate_number")
        private String plateNumber;

        @JsonProperty("formatted_plate_number")
        private String formattedPlateNumber;

        @JsonProperty("tech_pass_number")
        private String techPassNumber;

        @JsonProperty("invoice_number")
        private String invoiceNumber;

        @JsonProperty("license_photo_url")
        private String licensePhotoUrl;

        @JsonProperty("vehicle_url")
        private String vehicleUrl;

        @JsonProperty("video_url")
        private String videoUrl;

        @JsonProperty("discount_start_date")
        private String discountStartDate;

        @JsonProperty("car_photo_url")
        private String carPhotoUrl;

        @JsonProperty("full_photo_url")
        private String fullPhotoUrl;

        @JsonProperty("video_raw_url")
        private String videoRawUrl;

        @JsonProperty("fine_source")
        private String fineSource;

        @JsonProperty("fine_source_name")
        private String fineSourceName;

        @JsonProperty("discount_percentage")
        private Integer discountPercentage;

        @JsonProperty("discount_deadline")
        private String discountDeadline;

        @JsonProperty("amount_with_discount")
        private Long amountWithDiscount;

        @JsonProperty("progress_step")
        private Integer progressStep;

        @JsonProperty("progress_percentage")
        private Integer progressPercentage;

        @JsonProperty("progress_title")
        private String progressTitle;

        @JsonProperty("progress_left_days")
        private String progressLeftDays;

        @JsonProperty("decision_file_url")
        private String decisionFileUrl;
    }

    @Getter
    static class Violation {
        private Integer id;

        private String name;

        private String band;

        private String note;
    }
}