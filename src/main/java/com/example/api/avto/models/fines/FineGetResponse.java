package com.example.api.avto.models.fines;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.avto.BaseModelResponse;

@Getter
public class FineGetResponse extends BaseModelResponse<FineGetResponse.FineData> {

    @Getter
    public static class FineData {
        @JsonProperty("amount_to_pay")
        private Long amountToPay;

        @JsonProperty("get_info")
        private Boolean getInfo;

        private Fine fine;

        @Getter
        static class Fine {
            private Integer id;

            private String date;

            @JsonProperty("qaror_date")
            private String qarorDate;

            private Double lat;

            private Double lng;

            private Violation violation;

            private Status status;

            @JsonProperty("sery_number")
            private String seryNumber;

            private Long amount;

            @JsonProperty("amount_to_pay")
            private Long amountToPay;

            private String place;

            @JsonProperty("plate_number")
            private String plateNumber;

            @JsonProperty("formatted_plate_number")
            private String formattedPlateNumber;

            @JsonProperty("invoice_number")
            private String invoiceNumber;

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

        @Getter
        static class Status {
            private Integer id;

            private String name;
        }
    }
}