package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.Project;
import helpers.AllureAttachments;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    public static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        System.out.println(Project.deviceConfig.hubUrl());
        Configuration.browser = "drivers." + Project.deviceConfig.driver();
        Configuration.startMaximized = false;
        Configuration.browserSize = null;
        Configuration.timeout = 10000;
    }

    @AfterEach
    public void allureAttachments() {
        AllureAttachments.addAttachments(Project.deviceConfig.driver());
    }
}
