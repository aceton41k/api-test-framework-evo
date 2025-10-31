package com.example.api.autopayments.autopayment_ms;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreateAutoPayResponse {
    private Integer id;
    private Integer type;
    private Long datetime;
    private Integer serviceId;
    private String name;
    private BigDecimal amount;
    private Integer cntrgParamId;
    private String cntrgParam2;
    private String cntrgParam3;
    private String cntrgParam4;
    private String cntrgParam5;
    private String startDate;
    private Integer daysIntervalAmount;
    private String paytime;
    private Integer weeklyDay;
    private Integer monthlyDay;
    private Integer step;
    private Integer accountId;
    private Integer status;
}
