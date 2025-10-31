package com.example.api.avto.models.osago;

import lombok.Getter;
import com.example.api.avto.BaseModelResponse;

@Getter
public class OSAGODriverPostResponse extends BaseModelResponse<OSAGODriverPostResponse.PersonData> {

    @Getter
    public static class PersonData {
        private String hash;
        private String name;
        private Integer relative;
    }
}
