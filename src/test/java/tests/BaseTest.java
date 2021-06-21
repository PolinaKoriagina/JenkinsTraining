package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.CredentialManagerImpl;
import utils.SystemPropertyReader;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {
    @BeforeAll
    static void setUp() {
        // choose size of browser, added Allure, added screenshot,
        // page sources, browser console logs, enabled Video,
        // added remote test running with Selenoid
        final String login = CredentialManagerImpl.getCredConfig().getLogin();
        final String password = CredentialManagerImpl.getCredConfig().getPassword();
        final String remoteUrl = SystemPropertyReader.getSelenoidUrl();
        final String selenoidUrl = String.format("https://%s:%s@%s", login, password, remoteUrl);

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.startMaximized = true;
        Configuration.remote = selenoidUrl + "/wd/hub/";

    }

    //        String sessionId = getSessionId();
    public static String getSessionId() {
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
    }

    @AfterEach
    public void tearDown() {
        String sessionId = getSessionId();
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        closeWebDriver();
        Attach.addVideo(sessionId);
    }

}

