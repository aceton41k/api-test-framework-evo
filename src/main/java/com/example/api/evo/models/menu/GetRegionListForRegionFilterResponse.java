package com.example.api.evo.models.menu;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.evo.models.BaseResponse;

import java.util.List;

public class GetRegionListForRegionFilterResponse extends BaseResponse<List<GetRegionListForRegionFilterResponse.Result>> {
    @Getter
    public static class Result {

        private String id;
        private String title;
        @JsonProperty("is_selected")
        private Boolean isSelected;
    }
}
