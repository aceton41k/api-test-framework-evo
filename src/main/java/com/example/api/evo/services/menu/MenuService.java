package com.example.api.evo.services.menu;

import java.util.Map;

import com.example.api.evo.models.menu.*;
import com.example.evo.models.menu.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import uz.click.evo_api.models.menu.*;
import com.example.utils.DateTimeUtil;

@Service
public class MenuService {
    @Autowired
    EvoApiRequestSpecification evoReq;

    public <T> T addFavoriteMenus(
            MenuFavoritesAddRequest.Params params, Map<String, String> headers, Class<T> responseType) {
        var request = MenuFavoritesAddRequest.builder()
                .method("menu.favorites.add")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return evoReq.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(responseType);
    }

    public GetMenuResponse getMenuFavoritesList(GetMenuRequest.Params params, Map<String, String> headers) {
        var request = GetMenuRequest.builder()
                .method("menu.favorites.list")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return evoReq.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(GetMenuResponse.class);
    }

    public WhiteListResponse getWebViewWhiteList() {
        var request = RequestWithoutParams.builder()
                .method("webview.whitelist")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return evoReq.getRequestSpecification().body(request).post().as(WhiteListResponse.class);
    }

    public GetMenuResponse searchMenu(MenuSearchRequest.Params params, Map<String, String> headers) {
        var request = MenuSearchRequest.builder()
                .method("menu.search")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return evoReq.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(GetMenuResponse.class);
    }

    public MenuSearchGetDefaultsResponse searchMenuGetDefaults(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("menu.search.get.defaults")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return evoReq.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(MenuSearchGetDefaultsResponse.class);
    }
    public GetRegionListForRegionFilterResponse getRegionListForRegionFilter(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("mini.app.get.regions")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return evoReq.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(GetRegionListForRegionFilterResponse.class);
    }
}
