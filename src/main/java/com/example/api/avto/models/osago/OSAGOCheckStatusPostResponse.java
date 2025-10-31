package com.example.api.avto.models.osago;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.avto.BaseModelResponse;

@Getter
public class OSAGOCheckStatusPostResponse extends BaseModelResponse<OSAGOCheckStatusPostResponse.CheckData> {

    @Getter
    public static class CheckData {
        private Integer status;
        @JsonProperty("check_id")
        private Integer checkId;
    }
}
