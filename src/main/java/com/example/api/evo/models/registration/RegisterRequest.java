package com.example.api.evo.models.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import com.example.api.evo.models.BaseRequest;
import com.example.utils.TokenGenerator;

@SuperBuilder
public class RegisterRequest extends BaseRequest<RegisterRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("device_id")
        private String deviceId;

        private String pin;

        @JsonProperty("register_token")
        private String registerToken;

        private String name;
        private String surname;
        private String patronym;
        private Long birthdate;

        @JsonProperty("region_code")
        private String regionCode;

        private String gender;

        @JsonProperty("open_wallet")
        private Boolean openWallet;

        @JsonProperty("phone_number")
        private String phoneNumber;

        public static Params createDefault(String phone, String registerToken, String deviceId, String pin) {
            return Params.builder()
                    .phoneNumber(phone)
                    .deviceId(deviceId)
                    .pin(TokenGenerator.encodePin(pin))
                    .registerToken(registerToken)
                    .name("Зинаида")
                    .surname("Шмель")
                    .patronym("Оксановна")
                    .birthdate(631130400000L)
                    .regionCode("03")
                    .gender("F")
                    .openWallet(false)
                    .build();
        }
    }
}
