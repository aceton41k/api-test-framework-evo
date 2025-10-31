package com.example.api.evo.models.bnpl;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.evo.models.BaseResponse;


public class BnplPaymentResponse extends BaseResponse<BnplPaymentResponse.Result> {

    @Getter
    public static class Result {
        @JsonProperty("checkout_id")
        private String checkoutId;

        private Plans plans;
    }

    @Getter
    public static class Plans {
        private List<Plan> plans;

        @JsonProperty("default_plan")
        private String defaultPlan;
    }

    @Getter
    public static class Plan {
        private String id;

        @JsonProperty("product_type")
        private String productType;

        private String constructor;
        private List<Payment> payments;
        private String sum;
        private String currency;
        private String fee;

        @JsonProperty("constructor_details")
        private ConstructorDetails constructorDetails;
    }

    @Getter
    public static class Payment {
        private String amount;
        private String datetime;
        private String status;
    }

    @Getter
    public static class ConstructorDetails {
        @JsonProperty("length_in_months")
        private Integer lengthInMonths;

        @JsonProperty("payments_interval")
        private PaymentsInterval paymentsInterval;
    }

    @Getter
    public static class PaymentsInterval {
        private Integer value;
        private String type;
    }
}
