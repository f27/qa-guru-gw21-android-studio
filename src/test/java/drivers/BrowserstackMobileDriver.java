package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.Project;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {
    @Nonnull
    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        capabilities.setCapability("browserstack.user", Project.deviceConfig.hubUsername());
        capabilities.setCapability("browserstack.key", Project.deviceConfig.hubPassword());
        capabilities.setCapability("app", Project.deviceConfig.appBs());
        capabilities.setCapability("device", Project.deviceConfig.device());
        capabilities.setCapability("os_version", Project.deviceConfig.platformVersion());
        capabilities.setCapability("project", Project.deviceConfig.bsProject());
        capabilities.setCapability("build", Project.deviceConfig.bsBuild());
        capabilities.setCapability("name", Project.deviceConfig.bsName());

        return new AndroidDriver(getBrowserstackUrl(), capabilities);
    }

    private static URL getBrowserstackUrl() {
        try {
            return new URL(
                    String.format(
                            Project.deviceConfig.hubUrl(),
                            Project.deviceConfig.hubUsername(),
                            Project.deviceConfig.hubPassword())
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
