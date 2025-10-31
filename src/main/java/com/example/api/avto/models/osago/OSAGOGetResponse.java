package com.example.api.avto.models.osago;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.avto.BaseModelResponse;
import com.example.api.avto.models.vehicles.Owner;

import java.util.List;

@Getter
public class OSAGOGetResponse extends BaseModelResponse<OSAGOGetResponse.Data> {
    @Getter
    public static class Data {
        @JsonProperty("vehicle_id")
        private Integer vehicleId;

        @JsonProperty("vehicle_owner")
        private Owner owner;

        @JsonProperty("issue_date")
        private String issueDate;

        private Applicant applicant;

        @JsonProperty("expiry_date")
        private String expiryDate;

        @JsonProperty("driver_limit")
        private Boolean driverLimit;

        private List<Object> drivers;

        @JsonProperty("insurance_company_id")
        private Integer insuranceCompanyId;

        @JsonProperty("insurance_companies")
        private List<InsuranceCompany> insuranceCompanies;
    }

    @Getter
    public static class Applicant {
        @JsonProperty("region_id")
        private Integer regionId;

        @JsonProperty("district_id")
        private Integer districtId;

        @JsonProperty("passport_issued_by")
        private String passportIssuedBy;

        private String address;
    }

    @Getter
    public static class InsuranceCompany {
        private Integer id;
        private String name;
        private String rating;
        private Offers offers;
        private String logo;

        @Getter
        public static class Offers {
            private String osago;

            @JsonProperty("kasko_subscription")
            private String kaskoSubscription;
        }
    }
}