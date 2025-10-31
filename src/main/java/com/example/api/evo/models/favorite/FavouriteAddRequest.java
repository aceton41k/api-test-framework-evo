package com.example.api.evo.models.favorite;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import com.example.api.evo.models.BaseRequest;

import static com.example.api.evo.constants.ServiceData.API_VERSION;

@SuperBuilder
public class FavouriteAddRequest extends BaseRequest<FavouriteAddRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("service_id")
        private Integer serviceId;

        @JsonProperty("api_version")
        @Builder.Default
        private Integer apiVersion = API_VERSION;

        private Parameters parameters;
    }

    @Getter
    @SuperBuilder
    public static class Parameters {
        private String amount;

        @JsonProperty("phone_num")
        private String phoneNum;

        @JsonProperty("favorite_name")
        private String favoriteName;
    }
}
