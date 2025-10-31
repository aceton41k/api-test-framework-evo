package com.example.api.evo.services.transfer_p2p;

import java.util.Map;

import com.example.api.evo.models.transfer_p2p.*;
import com.example.evo.models.transfer_p2p.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import uz.click.evo_api.models.transfer_p2p.*;
import com.example.utils.DateTimeUtil;

@Service
public class TransferService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    public TransferInfoResponse getTransferInfo(TransferInfoRequest.Params params, Map<String, String> headers) {
        var request = TransferInfoRequest.builder()
                .method("transfer.info")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(TransferInfoResponse.class);
    }

    public GetTransferConfirmationResponse getTransferConfirmation(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("transfer.confirmation.get")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(GetTransferConfirmationResponse.class);
    }

    public GetTransferConfirmationResponse updateTransferConfirmation(
            TransferConfirmationUpdateRequest.Params params, Map<String, String> headers) {
        var request = TransferConfirmationUpdateRequest.builder()
                .method("transfer.confirmation.update")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(GetTransferConfirmationResponse.class);
    }

    public IssuerListResponse getIssuerList(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("issuer.list")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(IssuerListResponse.class);
    }
}
