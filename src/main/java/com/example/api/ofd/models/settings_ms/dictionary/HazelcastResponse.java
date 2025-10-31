package com.example.api.ofd.models.settings_ms.dictionary;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class HazelcastResponse {
    private Boolean success;
    private Integer code;
    private String reason;
    private List<Data> data;
    private Object errors;

    @Getter
    @Builder
    public static class Data {
        private String mxikCode;
        private Info info;

        @Getter
        @Builder
        public static class Info {
            private String mxikCode;
            private String internationalCode;
            private String subPositionName;
            private String brandName;
            private String name;
            private String attributName;
            private String nameUzCyrl;
            private String nameUzLatin;
            private String nameRu;
            private String unitCode;
            private String unitName;
            private String commonUnitCode;
            private String commonUnitName;
            private Integer label;
            private Integer labelTNN;
            private String usePackage;
            private String useLicense;
            private String isAllowedForOneSidedFactura;
            private Integer units;
            private List<PackageNames> packageNames;

            @Getter
            @Builder
            public static class PackageNames {
                private Integer code;
                private String mxikCode;
                private String nameUz;
                private String packageType;
                private String nameRu;
                private String nameLat;
            }
        }
    }
}
