package com.example.api.evo.models.wallet;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class CreateWalletRequest extends BaseRequest<CreateWalletRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        private Long birthdate;
    }
}
