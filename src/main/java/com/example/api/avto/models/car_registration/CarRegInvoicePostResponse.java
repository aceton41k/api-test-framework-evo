package com.example.api.avto.models.car_registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.avto.BaseModelResponse;

import java.util.List;

@Getter
public class CarRegInvoicePostResponse extends BaseModelResponse<List<CarRegInvoicePostResponse.Invoice>> {

    @Getter
    public static class Invoice {
        private Integer id;

        @JsonProperty("plate_number")
        private String plateNumber;

        @JsonProperty("invoice_number")
        private String invoiceNumber;

        @JsonProperty("invoice_amount")
        private Long invoiceAmount;

        @JsonProperty("service_id")
        private Integer serviceId;

        private String status;

        @JsonProperty("status_name")
        private String statusName;
    }
}
