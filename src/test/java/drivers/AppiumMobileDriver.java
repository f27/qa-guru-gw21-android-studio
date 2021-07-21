package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.Project;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class AppiumMobileDriver implements WebDriverProvider {
    @Nonnull
    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        capabilities.setCapability("platformName", Project.deviceConfig.platformName());
        capabilities.setCapability("deviceName", Project.deviceConfig.device());
        capabilities.setCapability("version", Project.deviceConfig.platformVersion());
        capabilities.setCapability("locale", Project.deviceConfig.platformLocale());
        capabilities.setCapability("language", Project.deviceConfig.platformLanguage());
        capabilities.setCapability("appPackage", Project.deviceConfig.appPackage());
        capabilities.setCapability("appActivity", Project.deviceConfig.appActivity());
        capabilities.setCapability("app", Project.deviceConfig.appUrl());

        return new AndroidDriver(getAppiumUrl(), capabilities);
    }

    private static URL getAppiumUrl() {
        try {
            return new URL(
                    Project.deviceConfig.hubUrl()
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
