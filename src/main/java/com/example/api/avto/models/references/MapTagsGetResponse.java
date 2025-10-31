package com.example.api.avto.models.references;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.avto.BaseModelResponse;

import java.util.List;

@Getter
public class MapTagsGetResponse extends BaseModelResponse<MapTagsGetResponse.Data> {

    @Getter
    public static class Data {
        private Gas gas;
        private Petrol petrol;
        private Diesel diesel;
    }

    @Getter
    public static class Gas {
        private String title;
        private List<Tag> tags;
    }

    @Getter
    public static class Petrol {
        private String title;
        private List<Tag> tags;
    }

    @Getter
    public static class Diesel {
        private String title;
        private List<Tag> tags;
    }

    @Getter
    public static class Tag {
        private Integer id;
        private String name;
        @JsonProperty("min_price")
        private Integer minPrice;
    }
}
