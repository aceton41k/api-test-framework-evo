package com.example.api.avto.models.osago;

import lombok.Getter;
import com.example.api.avto.BaseModelResponse;

import java.util.List;

@Getter
public class OSAGORelativesResponse extends BaseModelResponse<List<OSAGORelativesResponse.Relationship>> {

    @Getter
    public static class Relationship {
        private Integer id;
        private String name;
    }
}
