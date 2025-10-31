package com.example.api.evo.models.indoor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import com.example.api.evo.models.BaseRequest;
import com.example.api.evo.models.Location;

import static com.example.api.evo.constants.ServiceData.API_VERSION;

@SuperBuilder
public class IndoorServiceListRequest extends BaseRequest<IndoorServiceListRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @Builder.Default
        @JsonProperty("api_version")
        private Integer apiVersion = API_VERSION;
        @Builder.Default
        @JsonProperty("category_id")
        private Integer categoryId = 1;
        @Builder.Default
        @JsonProperty("page_number")
        private Integer pageNumber = 1;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Location location;
    }
}
