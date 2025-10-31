package com.example.api.evo.models.favorite;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class FavouriteDeleteRequest extends BaseRequest<FavouriteDeleteRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        private String id;
    }
}
