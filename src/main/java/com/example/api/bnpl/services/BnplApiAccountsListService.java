package com.example.api.bnpl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.bnpl.BnplApiRequestSpecification;
import com.example.api.bnpl.models.AccountsListResponse;

@Service
public class BnplApiAccountsListService {

    @Autowired
    BnplApiRequestSpecification reqSpec;

    public AccountsListResponse getAccountsList(int clientId) {
        return reqSpec.build()
                .queryParam("clientId", clientId)
                .get("/api/bnpl/partner/v1/accounts/list")
                .as(AccountsListResponse.class);
    }
}
