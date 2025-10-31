package com.example.api.evo.models.tickets;

import java.util.List;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class GetTicketTypesResponse extends BaseResponse<List<GetTicketTypesResponse.Result>> {
    @Getter
    public static class Result {
        private String type;
        private String name;
    }
}
