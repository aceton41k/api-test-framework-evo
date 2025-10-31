package com.example.api.bnpl.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class CheckoutResponse {
    private String status;
    private String message;
    private Data data;

    @Getter
    public static class Data {

        @JsonProperty("provider_name")
        private String providerName;

        @JsonProperty("frontend_provider_url")
        private String frontendProviderUrl;

        @JsonProperty("status_code")
        private Integer statusCode;

        @JsonProperty("status_description")
        private String statusDescription;

        private Application application;

        @Getter
        public static class Application {

            @JsonProperty("provider_id")
            private Integer providerId;

            @JsonProperty("checkout_id")
            private String checkoutId;

            @JsonProperty("transaction_type")
            private String transactionType;

            @JsonProperty("internal_merchant_service_id")
            private Integer internalMerchantServiceId;

            @JsonProperty("internal_merchant_service_name")
            private String internalMerchantServiceName;

            @JsonProperty("internal_geo")
            private String internalGeo;

            @JsonProperty("internal_total_amount")
            private Integer internalTotalAmount;

            @JsonProperty("internal_device_id")
            private String internalDeviceId;

            @JsonProperty("bnpl_user_id")
            private Integer bnplUserId;

            @JsonProperty("bnpl_status_id")
            private Integer bnplStatusId;

            @JsonProperty("bnpl_service_id")
            private Integer bnplServiceId;

            @JsonProperty("internal_basket")
            private String internalBasket;

            @JsonIgnore
            private ResAtr resAtr;

            @JsonProperty("res_atr")
            private void deserializeResAtr(String value) throws Exception {
                ObjectMapper mapper = new ObjectMapper();
                this.resAtr = mapper.readValue(value, ResAtr.class);
            }

            @JsonIgnore
            private ReqAtr reqAtr;

            @JsonProperty("req_atr")
            private void deserializeReqAtr(String value) throws Exception {
                ObjectMapper mapper = new ObjectMapper();
                this.reqAtr = mapper.readValue(value, ReqAtr.class);
            }

            private String fee;

            @JsonProperty("updated_at")
            private String updatedAt;

            @JsonProperty("created_at")
            private String createdAt;

            private Integer id;

            @Getter
            public static class ResAtr {
                @JsonIgnore
                private Res res;

                @JsonProperty("res")
                private void deserializeRes(String value) throws Exception{
                    ObjectMapper mapper = new ObjectMapper();
                    this.res = mapper.readValue(value, Res.class);
                }

                @Getter
                public static class Res {

                    private List<Plan> plans;

                    @JsonProperty("default_plan")
                    private String defaultPlan;

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
                        private int lengthInMonths;

                        @JsonProperty("payments_interval")
                        private PaymentsInterval paymentsInterval;
                    }

                    @Getter
                    public static class PaymentsInterval {
                        private Integer value;
                        private String type;
                    }
                }
            }

            @Getter
            public static class ReqAtr {

                @JsonProperty("checkout_id")
                private String checkoutId;

                @JsonProperty("user_id")
                private String userId;

                private Checkout checkout;

                @Getter
                public static class Checkout {
                    private String amount;
                    private String currency;
                    private Geo geo;
                    private Merchant merchant;
                    private List<Item> items;

                    @Getter
                    public static class Geo {
                        private String latitude;
                        private String longitude;
                    }

                    @Getter
                    public static class Merchant {
                        private String name;
                        private String id;
                        private String logo;
                    }

                    @Getter
                    public static class Item {
                        private String id;
                        private String name;
                        private Integer count;
                        private String amount;
                        private String currency;
                        @JsonProperty("total_amount")
                        private String totalAmount;
                    }
                }
            }
        }
    }
}
