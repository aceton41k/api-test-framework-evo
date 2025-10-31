package com.example.api.ofd.models.settings_ms.dictionary;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class HazelcastRequest {
    private List<String> mxikCodes;
}
