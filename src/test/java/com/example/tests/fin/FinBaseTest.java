package com.example.tests.fin;

import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.Application;
import com.example.api.evo.Operations;
import com.example.api.evo.constants.UserData;
import com.example.asserts.ClickSoftAssertions;


@ExtendWith(SoftAssertionsExtension.class)
@SpringBootTest(classes = Application.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class FinBaseTest {

    @InjectSoftAssertions
    protected ClickSoftAssertions soft;

    @Autowired
    protected Operations operations;
    @Autowired
    protected UserData userData;

    protected String webSession;
}
