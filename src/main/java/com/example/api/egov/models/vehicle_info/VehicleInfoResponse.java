package com.example.api.egov.models.vehicle_info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class VehicleInfoResponse {

    @JsonProperty("pResult")
    private int pResult;

    @JsonProperty("pComment")
    private String pComment;

    @JsonProperty("pPinpp")
    private String pPinpp;

    @JsonProperty("pOwner")
    private String pOwner;

    @JsonProperty("pOwnerType")
    private Integer pOwnerType;

    @JsonProperty("pOrganizationInn")
    private String pOrganizationInn;

    @JsonProperty("Vehicle")
    private List<Vehicle> vehicle;

    @Getter
    public static class Vehicle {

        @JsonProperty("pRegistrationDate")
        private String pRegistrationDate;

        @JsonProperty("pDateSchetSpravka")
        private String pDateSchetSpravka;

        @JsonProperty("pTuningGivenDate")
        private String pTuningGivenDate;

        @JsonProperty("pTuningIssueDate")
        private String pTuningIssueDate;

        // дублирующие поля из JSON
        @JsonProperty("pregistrationDate")
        private String pregistrationDate;

        @JsonProperty("pdateSchetSpravka")
        private String pdateSchetSpravka;

        @JsonProperty("ptuningGivenDate")
        private String ptuningGivenDate;

        @JsonProperty("ptuningIssueDate")
        private String ptuningIssueDate;

        @JsonProperty("pVehicleId")
        private Integer pVehicleId;

        @JsonProperty("pTexpassportSery")
        private String pTexpassportSery;

        @JsonProperty("pTexpassportNumber")
        private String pTexpassportNumber;

        @JsonProperty("pPlateNumber")
        private String pPlateNumber;

        @JsonProperty("pModel")
        private String pModel;

        @JsonProperty("pVehicleColor")
        private String pVehicleColor;

        @JsonProperty("pDivision")
        private String pDivision;

        @JsonProperty("pYear")
        private Integer pYear;

        @JsonProperty("pVehicleType")
        private Integer pVehicleType;

        @JsonProperty("pKuzov")
        private String pKuzov;

        @JsonProperty("pShassi")
        private String pShassi;

        @JsonProperty("pFullWeight")
        private Integer pFullWeight;

        @JsonProperty("pEmptyWeight")
        private Integer pEmptyWeight;

        @JsonProperty("pMotor")
        private String pMotor;

        @JsonProperty("pFuelType")
        private Integer pFuelType;

        @JsonProperty("pSeats")
        private Integer pSeats;

        @JsonProperty("pStands")
        private Integer pStands;

        @JsonProperty("pComments")
        private String pComments;

        @JsonProperty("pPower")
        private Integer pPower;

        @JsonProperty("pTuningPermit")
        private String pTuningPermit;

        @JsonProperty("prevPnfl")
        private String prevPnfl;

        @JsonProperty("prevOwner")
        private String prevOwner;

        @JsonProperty("prevOwnerType")
        private Integer prevOwnerType;

        @JsonProperty("prevPlateNumber")
        private String prevPlateNumber;

        @JsonProperty("prevTexpasportSery")
        private String prevTexpasportSery;

        @JsonProperty("prevTexpasportNumber")
        private String prevTexpasportNumber;
    }
}
