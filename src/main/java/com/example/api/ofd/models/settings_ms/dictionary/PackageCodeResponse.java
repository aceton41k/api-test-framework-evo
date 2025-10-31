package com.example.api.ofd.models.settings_ms.dictionary;

import lombok.Getter;

@Getter
public class PackageCodeResponse {
    private Integer code;
    private String mxikCode;
    private String nameUz;
    private String nameRu;
    private String nameLat;
    private String packageType;
    private Boolean createdByOperator;
}
