package com.example.api.avto.models.user_token;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.avto.models.vehicles.Vehicle;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetUserTokenResponse {

    private String token;

    @JsonProperty("click_user")
    private ClickUser clickUser;

    private List<Vehicle> vehicles;

    @JsonProperty("unread_notifications")
    private Integer unreadNotifications;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ClickUser {
        private Integer id;

        @JsonProperty("client_id")
        private Integer clientId;

        @JsonProperty("phone_number")
        private Long phoneNumber;

        @JsonProperty("region_code")
        private String regionCode;

        private String language;

        @JsonProperty("is_identified")
        private Boolean isIdentified;

        @JsonProperty("is_developer")
        private Boolean isDeveloper;

        @JsonProperty("is_employee")
        private Boolean isEmployee;

        private Meta meta;

        @Getter
        public static class Meta {
            @JsonProperty("main_onboarding")
            private List<String> mainOnboarding;

            @JsonProperty("empty_onboard")
            private Boolean emptyOnboard;

            private String test;
        }
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Offer {
        private String type;
        @JsonProperty("file_ru")
        private String fileRu;
        @JsonProperty("file_uz")
        private String fileUz;
        @JsonProperty("file_en")
        private String fileEn;
    }
}

