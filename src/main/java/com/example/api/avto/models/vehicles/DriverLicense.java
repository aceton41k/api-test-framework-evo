package com.example.api.avto.models.vehicles;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class DriverLicense {

    @JsonProperty("issued_date")
    private String issuedDate;
    @JsonProperty("expiry_date")
    private String expiryDate;
    private String name;
    private String surname;
    private String patronymic;
    private String series;
    private String number;
    private String category;

}
