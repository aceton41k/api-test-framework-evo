package com.example.api.evo.services.wallet;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import com.example.api.evo.models.ResponseWithOkResult;
import com.example.api.evo.models.wallet.CreateWalletRequest;
import com.example.api.evo.models.wallet.CreateWalletResponse;
import com.example.api.evo.models.wallet.DeleteWalletRequest;
import com.example.api.evo.models.wallet.WalletRatesResponse;
import com.example.utils.DateTimeUtil;

@Service
public class EvoWalletService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    public CreateWalletResponse createWallet(CreateWalletRequest.Params params, Map<String, String> headers) {
        var request = CreateWalletRequest.builder()
                .method("wallet.create")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(CreateWalletResponse.class);
    }

    public ResponseWithOkResult deleteWallet(DeleteWalletRequest.Params params, Map<String, String> headers) {
        var request = DeleteWalletRequest.builder()
                .method("wallet.delete")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ResponseWithOkResult.class);
    }

    public WalletRatesResponse getWalletRates(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("wallet.rates")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(WalletRatesResponse.class);
    }
}
