package com.example.api.evo.services.settings;

import java.io.File;
import java.util.Map;

import com.example.api.evo.models.settings.*;
import com.example.evo.models.settings.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.restassured.http.ContentType;

import com.example.api.evo.EvoApiRequestSpecification;
import com.example.api.evo.models.RequestWithoutParams;
import com.example.api.evo.models.ResponseWithOkResult;
import uz.click.evo_api.models.settings.*;
import com.example.utils.DateTimeUtil;

@Service
public class SettingsService {
    @Autowired
    private EvoApiRequestSpecification reqSpec;

    public GetUserProfileResponse getUserProfile(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("get.user.profile")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(GetUserProfileResponse.class);
    }

    public SettingsUploadPhotoResponse uploadPhoto(Map<String, String> headers, File file) {
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .contentType(ContentType.MULTIPART) // Явно указываем multipart
                .multiPart("method", "settings.photo.upload")
                .multiPart("image", file)
                .post()
                .as(SettingsUploadPhotoResponse.class);
    }

    public ResponseWithOkResult removeUserPhoto(Map<String, String> headers) {
        var request = RequestWithoutParams.builder()
                .method("settings.photo.remove")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(RequestWithoutParams.Params.builder().build())
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ResponseWithOkResult.class);
    }

    public ResponseWithOkResult changeDefaultAccount(
            SettingsChangeDefaultAccountRequest.Params params, Map<String, String> headers) {
        var request = SettingsChangeDefaultAccountRequest.builder()
                .method("settings.change.default.account")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ResponseWithOkResult.class);
    }

    public ResponseWithOkResult changePin(SettingsChangePinRequest.Params params, Map<String, String> headers) {
        var request = SettingsChangePinRequest.builder()
                .method("settings.change.pin")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ResponseWithOkResult.class);
    }

    public ResponseWithOkResult changeLanguage(ChangeLanguageRequest.Params params, Map<String, String> headers) {
        var request = ChangeLanguageRequest.builder()
                .method("change.language")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(ResponseWithOkResult.class);
    }

    public SettingsChangeProfileResponse changeProfile(
            SettingsChangeProfileRequest.Params params, Map<String, String> headers) {
        var request = SettingsChangeProfileRequest.builder()
                .method("settings.change.profile")
                .id(DateTimeUtil.getCurrentTimestamp())
                .params(params)
                .build();
        return reqSpec.getRequestSpecification()
                .headers(headers)
                .body(request)
                .post()
                .as(SettingsChangeProfileResponse.class);
    }
}
