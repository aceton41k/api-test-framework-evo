package com.example.api.egov.constants;

import org.opentest4j.TestAbortedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EGovData {

    @Autowired
    private Environment env;

    public String getEGovLogin() {
        return getEnvValue("EGOV_LOGIN");
    }

    public String getEGovPassword() {
        return getEnvValue("EGOV_PASSWORD");
    }

    @SuppressWarnings("CallToPrintStackTrace")
    private String getEnvValue(String envName) {
        String msg = "Environment variable '%s' is not set. Contact responsible QA to get actual value".formatted(envName);
        String value = env.getProperty(envName);
        if (value == null) {
            var e = new TestAbortedException(msg);
            e.printStackTrace();
            throw e;
        }
        return value;
    }
}