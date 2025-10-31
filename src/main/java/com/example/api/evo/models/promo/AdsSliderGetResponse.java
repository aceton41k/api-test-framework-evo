package com.example.api.evo.models.promo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.evo.models.BaseResponse;

import java.util.List;

public class AdsSliderGetResponse extends BaseResponse<List<AdsSliderGetResponse.Result>> {
    @Getter
    public static class Result {
        private String  id;
        private String  image;
        private String  url;

        @JsonProperty("analytics_variable")
        private String analyticsVariable;

        @JsonProperty("analytics_view_variable")
        private String analyticsViewVariable;
    }
}
