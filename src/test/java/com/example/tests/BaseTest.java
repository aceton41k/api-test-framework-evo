package com.example.tests;

import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.Application;
import com.example.api.evo.Operations;
import com.example.api.evo.constants.UserData;
import com.example.api.evo.services.DataBaseService;
import com.example.jupiter.extensions.ApiExtension;
import com.example.asserts.ClickSoftAssertions;

import java.util.Map;

@SpringBootTest(classes = Application.class)
@ExtendWith(SoftAssertionsExtension.class)
@ExtendWith(ApiExtension.class)
public abstract class BaseTest {

    @Deprecated
    public Map<String, String> deprecatedHeaders;
    @InjectSoftAssertions
    protected ClickSoftAssertions soft;
    @Autowired
    protected Operations operations;

    @Autowired
    protected UserData userData;
    @Autowired
    protected DataBaseService dataBaseService;
}
