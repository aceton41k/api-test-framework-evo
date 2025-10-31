package com.example.api.avto.models.vehicles;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.avto.BaseModelResponse;

import java.util.List;

@Getter
public class VehiclesOsagoPoliciesGetResponse extends BaseModelResponse<List<VehiclesOsagoPoliciesGetResponse.InsuranceData>> {

    @Getter
    public static class InsuranceData {
        private Integer id;
        private String serie;
        private String number;
        @JsonProperty("organization_short_name")
        private String organizationShortName;
        private String type;
        @JsonProperty("type_name")
        private String typeName;
        @JsonProperty("issue_date")
        private String issueDate;
        @JsonProperty("expiry_date")
        private String expiryDate;
        private Integer amount;
        private List<Driver> drivers;
        @JsonProperty("file_url")
        private String fileUrl;
        @JsonProperty("left_days")
        private int leftDays;
        @JsonProperty("left_days_name")
        private String leftDaysName;
        @JsonProperty("left_percent")
        private int leftPercent;
    }
}
