package com.example.api.ofd.models.price_list_ms;

import lombok.Getter;

import java.util.List;

@Getter
public class GoodsPricesResponse {
    private List<GoodsPriceOutputDto> goodsPriceOutputDtos;
    private int page;
    private int totalElements;
    private int totalPages;

    @Getter
    public static class GoodsPriceOutputDto {
        private Long id;
        private Integer serviceId;
        private String mxikCode;
        private Long pricePerUnit;
        private String priceDate;
    }
}
