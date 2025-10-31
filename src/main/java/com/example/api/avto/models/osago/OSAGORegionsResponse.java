package com.example.api.avto.models.osago;

import lombok.Getter;
import com.example.api.avto.BaseModelResponse;

import java.util.List;

@Getter
public class OSAGORegionsResponse extends BaseModelResponse<List<OSAGORegionsResponse.Region>> {

    @Getter
    public static class Region {
        private Integer id;
        private String name;
        private List<District> districts;
    }

    @Getter
    public static class District {
        private Long id;
        private String name;
    }
}