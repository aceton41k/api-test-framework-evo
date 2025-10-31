package com.example.api.avto.models.vehicles;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PassportData {

    private String pinfl;
    private String seria;
    private String number;
    @JsonProperty("issueDate")
    private LocalDate issueDate;
    @JsonProperty("issuedBy")
    private String issuedBy;

}
