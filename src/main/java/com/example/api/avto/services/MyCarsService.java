package com.example.api.avto.services;

import com.example.api.avto.models.notifications.*;
import com.example.click_avto_api.models.notifications.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.avto.BaseModelResponse;
import com.example.api.avto.MyAutoApiRequestSpecification;
import com.example.api.avto.models.fines.FineGetResponse;
import com.example.api.avto.models.fines.FinesListGetResponse;
import uz.click.click_avto_api.models.notifications.*;
import com.example.api.avto.models.sos.SosCategoriesGetResponse;
import com.example.api.avto.models.sos.SosServicesGetResponse;
import com.example.api.avto.models.user_token.GetUserTokenResponse;

import java.util.Map;

@Service
public class MyCarsService {
    @Autowired
    private MyAutoApiRequestSpecification reqSpec;

    public GetUserTokenResponse getUserToken(String authToken, String clientId) {

        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + authToken)
                .queryParam("client_id", clientId)
                .get("/internal/user")
                .as(GetUserTokenResponse.class);

    }
    public BaseModelResponse getDriverLicense(String token) {

        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .get("/v2/driver-license")
                .as(BaseModelResponse.class);
    }

    public BaseModelResponse getUserPassport(String token) {

        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .get("/v2/passport")
                .as(BaseModelResponse.class);
    }

    public NotificationsResponse getNotif(String token) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .get("/v2/notifications")
                .as(NotificationsResponse.class);
    }

    public NotificationsResponse markAllNotif(String token) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .post("/v2/notifications/read")
                .as(NotificationsResponse.class);
    }

    public FinesListGetResponse getFinesList(String token, Map<String, Object> params) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .queryParams(params)
                .get("/v2/fines")
                .as(FinesListGetResponse.class);
    }

    public FineGetResponse getFine(String token, int fineId) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .get("/v2/fines/" + fineId + "/info")
                .as(FineGetResponse.class);
    }

    public SosCategoriesGetResponse getSosCategories(String token) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .get("/v2/sos/categories")
                .as(SosCategoriesGetResponse.class);
    }

    public SosServicesGetResponse getSosServices(String token, int categoryId) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .get("/v2/sos/categories/"+categoryId+"/services")
                .as(SosServicesGetResponse.class);
    }

    public BaseModelResponse setWidgetHide(String token, HideWidgetPostRequest.Params params) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .body(params)
                .post("/v2/reminders/hide")
                .as(BaseModelResponse.class);
    }

    public MetadataPostResponse setMetadata(String token, MetadataPostRequest.Params params) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .body(params)
                .post("/v2/meta")
                .as(MetadataPostResponse.class);
    }

    public MetadataDeleteResponse deleteMetadata (String token, String key) {
        return reqSpec.getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .delete("/v2/meta/"+key)
                .as(MetadataDeleteResponse.class);
    }
}
