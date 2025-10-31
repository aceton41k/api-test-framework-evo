package com.example.api.evo.services.favorite;

import java.util.Map;
import java.util.Optional;

import com.example.api.evo.models.favorite.*;
import com.example.evo.models.favorite.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.ResponseWithOkResult;
import com.example.utils.DateTimeUtil;

@Service
public class FavoriteService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    public FavoriteListResponse getFavoriteList(FavouriteListRequest.Params params, Map<String, String> headers) {
        FavouriteListRequest request = FavouriteListRequest.builder()
                .method("favorite.list")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(FavoriteListResponse.class);
    }

    public Optional<FavoriteListResponse.Result> getFavoritePaymentByName(
            FavouriteListRequest.Params params, Map<String, String> headers, String favoriteName) {
        return this.getFavoriteList(params, headers).getResult().stream()
                .filter(home -> home.getName().equals(favoriteName))
                .findFirst();
    }

    public ResponseWithOkResult favoriteAdd(FavouriteAddRequest.Params params, Map<String, String> headers) {
        FavouriteAddRequest request = FavouriteAddRequest.builder()
                .method("favorite.add")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ResponseWithOkResult.class);
    }

    public ResponseWithOkResult addFavoriteFromPayment(
            FavoriteFromPaymentRequest.Params params, Map<String, String> headers) {
        FavoriteFromPaymentRequest request = FavoriteFromPaymentRequest.builder()
                .method("favorite.from.payment")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ResponseWithOkResult.class);
    }

    public ResponseWithOkResult deleteFavorite(FavouriteDeleteRequest.Params params, Map<String, String> headers) {
        FavouriteDeleteRequest request = FavouriteDeleteRequest.builder()
                .method("favorite.remove")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ResponseWithOkResult.class);
    }

    public ResponseWithOkResult editFavorite(FavoriteEditRequest.Params params, Map<String, String> headers) {
        FavoriteEditRequest request = FavoriteEditRequest.builder()
                .method("favorite.edit")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ResponseWithOkResult.class);
    }

    public FavoriteDataResponse getFavoriteData(FavoriteDataRequest.Params params, Map<String, String> headers) {
        FavoriteDataRequest request = FavoriteDataRequest.builder()
                .method("favorite.data")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(FavoriteDataResponse.class);
    }
}
