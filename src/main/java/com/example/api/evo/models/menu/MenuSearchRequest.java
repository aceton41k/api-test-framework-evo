package com.example.api.evo.models.menu;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class MenuSearchRequest extends BaseRequest<MenuSearchRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        private String search;
    }
}
