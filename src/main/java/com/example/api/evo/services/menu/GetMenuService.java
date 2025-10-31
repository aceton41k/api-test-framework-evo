package com.example.api.evo.services.menu;

import com.example.api.evo.models.menu.*;
import com.example.evo.models.menu.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import uz.click.evo_api.models.menu.*;
import com.example.utils.DateTimeUtil;

import java.util.Map;

@Service
public class GetMenuService {
    @Autowired
    EvoApiRequestSpecification reqSpec;

    public GetMenuResponse getMenu(GetMenuRequest.Params params, Map<String, String> headers) {
        var request = GetMenuRequest.builder()
                .method("get.menu")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(GetMenuResponse.class);
    }

    public GetMenuGroupResponse getMenuGroup(GetMenuRequest.Params params, Map<String, String> headers) {
        var request = GetMenuRequest.builder()
                .method("get.menu.group")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(GetMenuGroupResponse.class);
    }

    public GetMenuButtonsResponse getMenuButtons(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("get.menu.buttons")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(GetMenuButtonsResponse.class);
    }

    public GetMenuCategoriesResponse getMenuCategories(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("get.menu.categories")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(GetMenuCategoriesResponse.class);
    }

    public GetGuestSessionResponse getGuestSession(GetGuestSessionRequest.Params params, Map<String, String> headers) {
        var request = GetGuestSessionRequest.builder()
                .method("get.guest.session")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(GetGuestSessionResponse.class);
    }
}
