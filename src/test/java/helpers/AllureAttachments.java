package helpers;

import config.Project;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AllureAttachments {

    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] addScreenshotAs(String attachName) {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type = "text/html")
    public static byte[] addPageSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String attachVideo(String sessionId, String url) {
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + url
                + "' type='video/mp4'></video></body></html>";
    }

    public static void addAttachments(String driver) {
        String sessionId;
        switch(driver) {
            case "SelenoidMobileDriver":
                sessionId = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
                addScreenshotAs("Last screenshot");
                addPageSource();
                closeWebDriver();
                attachVideo(sessionId, Project.deviceConfig.selenoidVideoStorage() + sessionId + ".mp4");
                break;
            case "BrowserstackMobileDriver":
                sessionId = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
                addScreenshotAs("Last screenshot");
                addPageSource();
                closeWebDriver();
                attachVideo(sessionId, BrowserstackApi.getVideoUrl(sessionId));
                break;
            default:
                addScreenshotAs("Last screenshot");
                AllureAttachments.addPageSource();
                closeWebDriver();
                break;
        }
    }
}
