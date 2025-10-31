package com.example.api.avto.models.references;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.avto.BaseModelResponse;

import java.util.List;
import java.util.Map;

@Getter
public class MapResponse extends BaseModelResponse<MapResponse.Data> {

    @Getter
    public static class Data {
        private List<Filter> filter;
        private List<Location> locations;
    }

    @Getter
    public static class Filter {
        private Integer id;
        @JsonProperty("map_object_type_id")
        private Integer mapObjectTypeId;
        private String group;
        private Double price;
        private String name;
    }

    @Getter
    public static class Location {
        private Integer id;
        @JsonProperty("type_id")
        private Integer typeId;
        @JsonProperty("service_id")
        private String serviceId;
        @JsonProperty("merchant_id")
        private String merchantId;
        private String cashback;
        private String name;
        @JsonProperty("legal_name")
        private String legalName;
        private String lat;
        private String lng;
        private String address;
        private String deeplink;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Double distance;
        private String logo;
        @JsonProperty("price_from")
        private String priceFrom;
        private Map<String, FuelType> prices;
    }

    @Getter
    public static class FuelType {
        private String title;
        private List<Tag> tags;
    }

    @Getter
    public static class Tag {
        private Integer id;
        private String name;
        private Integer price;
    }
}
