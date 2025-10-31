package com.example.api.evo.models.misc;

import java.util.List;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class NearbyUsersDataRequest extends BaseRequest<NearbyUsersDataRequest.Params> {
    @SuperBuilder
    @Getter
    public static class Params {
        private List<Integer> users;
    }
}
