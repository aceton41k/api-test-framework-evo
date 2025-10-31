package com.example.api.egov.models.driverLicense;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class DriverLicenceResponse {

    @JsonProperty("pOwner_date")
    private String pOwnerDate;

    @JsonProperty("pBegin")
    private String pBegin;

    @JsonProperty("pEnd")
    private String pEnd;

    // дубли с маленькой буквы
    @JsonProperty("powner_date")
    private String pownerDate;

    @JsonProperty("pbegin")
    private String pbegin;

    @JsonProperty("pend")
    private String pend;

    @JsonProperty("pResult")
    private int pResult;

    @JsonProperty("pComment")
    private String pComment;

    @JsonProperty("pPinpp")
    private String pPinpp;

    @JsonProperty("pDoc")
    private String pDoc;

    @JsonProperty("pOwner")
    private String pOwner;

    @JsonProperty("pIssuedBy")
    private String pIssuedBy;

    @JsonProperty("pSerialNumber")
    private String pSerialNumber;

    @JsonProperty("Categories")
    private List<Category> categories;

    @Getter
    public static class Category {
        @JsonProperty("pBegin")
        private String pBegin;

        @JsonProperty("pEnd")
        private String pEnd;

        @JsonProperty("pbegin")
        private String pbegin;

        @JsonProperty("pend")
        private String pend;

        @JsonProperty("pCategory")
        private String pCategory;

        @JsonProperty("pComment")
        private String pComment;
    }

}
