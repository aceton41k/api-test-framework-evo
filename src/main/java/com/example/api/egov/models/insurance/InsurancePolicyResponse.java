package com.example.api.egov.models.insurance;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;

import java.util.List;

@Getter
public class InsurancePolicyResponse {
    private int error;

    @JsonAlias({"errorMessage", "error_message"})
    private String errorMessage;

    private List<InsurancePolicy> result;

    @Getter
    public static class InsurancePolicy {
        private String applicantName;
        private String applicantType;
        private String insuranceOrgName;
        private String policySeria;
        private String policyNumber;
        private String govNumber;
        private String policyType;
        private String policyStartDate;
        private String policyEndDate;
        private String vehicleModel;
        private String insurancePremium;
        private String insuranceSum;

        private List<Driver> drivers;

        private List<Object> accidentData;
    }

    @Getter
    public static class Driver {
        private String pinfl;
        private String firstname;
        private String lastname;
        private String middlename;
    }
}
