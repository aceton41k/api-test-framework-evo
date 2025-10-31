package com.example.api.evo.services.reports;

import java.util.Map;

import com.example.api.evo.models.reports.*;
import com.example.evo.models.reports.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import uz.click.evo_api.models.reports.*;
import com.example.utils.DateTimeUtil;

@Service
public class ReportsService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    public GetChartResponse getChart(GetChartRequest.Params params, Map<String, String> headers) {
        var request = GetChartRequest.builder()
                .method("get.chart")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(GetChartResponse.class);
    }

    public HistoryClickResponse getHistoryClick(HistoryClickRequest.Params params, Map<String, String> headers) {
        var request = HistoryClickRequest.builder()
                .method("history.click")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(HistoryClickResponse.class);
    }

    public Long getPaymentId(HistoryClickRequest.Params params, Map<String, String> headers) {
        return this.getHistoryClick(params, headers).getResult().getFirst().getPaymentId();
    }

    public HistoryLatestResponse getHistoryLatest(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("history.latest")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(HistoryLatestResponse.class);
    }

    public HistoryMonitoringResponse getHistoryMonitoring(
            HistoryClickRequest.Params params, Map<String, String> headers) {
        var request = HistoryClickRequest.builder()
                .method("history.monitoring")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(HistoryMonitoringResponse.class);
    }

    public PaymentHistoryResponse getPaymentHistory(PaymentHistoryRequest.Params params, Map<String, String> headers) {
        var request = PaymentHistoryRequest.builder()
                .method("payment.history")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(PaymentHistoryResponse.class);
    }
}
