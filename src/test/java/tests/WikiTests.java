package tests;

import io.appium.java_client.MobileBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.MobileBy.AccessibilityId;
import static io.qameta.allure.Allure.step;

public class WikiTests extends TestBase {

    @Test
    @DisplayName("Onborading page test")
    void onboardingPageTest() {
        step("Open app", () -> {
            open();
        });

        step("Onboarding page should have text 'The Free Encyclopedia …in over 300 languages'", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("The Free Encyclopedia …in over 300 languages"));
        });

        step("Click continue", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());

        step("Check title is [New ways to explore]", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("New ways to explore"));
        });

        step("Click continue", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());

        step("Check title is [Reading lists with sync]", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("Reading lists with sync"));
        });

        step("Click continue", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());

        step("Check title is [Send anonymous data]", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("Send anonymous data"));
        });

    }
}
