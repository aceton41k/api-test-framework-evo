package com.example.api.ofd.services;

import com.example.api.ofd.models.settings_ms.dictionary.*;
import com.example.ofd_api.models.settings_ms.dictionary.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.ofd.OfdRequestSpecification;
import uz.click.ofd_api.models.settings_ms.dictionary.*;

import java.util.List;
import java.util.Map;
import java.util.Set;


@Service
public class DictionaryService {
    private static final String OFD_DICTIONARY_BASE_PATH = "/v3/dictionary";

    @Autowired
    private OfdRequestSpecification ofdSpec;

    public MxikStatusResponse[] getStatusByMxikCode(List<String> mxikCode) {
        return ofdSpec.getSettingsReqSpec()
                .basePath(OFD_DICTIONARY_BASE_PATH)
                .body(mxikCode)
                .post("/mxik-status")
                .as(MxikStatusResponse[].class);
    }

    public PackageCodeResponse[] getPackageCode(Map<String, String> queryParams) {
        return ofdSpec.getSettingsReqSpec()
                .basePath(OFD_DICTIONARY_BASE_PATH)
                .queryParams(queryParams)
                .get("/package-code")
                .as(PackageCodeResponse[].class);
    }

    public MatchingResponse[] getMatchingList() {
        return ofdSpec.getSettingsReqSpec()
                .basePath(OFD_DICTIONARY_BASE_PATH)
                .get("/matching")
                .as(MatchingResponse[].class);
    }

    public MatchingResponse[] getPaymentType() {
        return ofdSpec.getSettingsReqSpec()
                .basePath(OFD_DICTIONARY_BASE_PATH)
                .get("/payment-type")
                .as(MatchingResponse[].class);
    }

    public Set<String> getExpectedPaymentTypeNames() {
        return Set.of(
                "PAYMENT_TYPE_1",
                "REFUND_TYPE_1",
                "PAYMENT_TYPE_3",
                "REFUND_TYPE_3",
                "PAYMENT_TYPE_4",
                "PAYMENT_TYPE_5"
        );
    }

    public HazelcastResponse checkHazelcast(HazelcastRequest request) {
        List<String> codes = request.getMxikCodes();

        return ofdSpec.getSettingsReqSpec()
                .basePath(OFD_DICTIONARY_BASE_PATH)
                .body(codes)
                .post("/check/hazelcast")
                .as(HazelcastResponse.class);
    }
}
