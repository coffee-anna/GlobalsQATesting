package Selenide.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.nio.file.Files;
import java.nio.file.Path;

public class BaseInitializationTest {

    @Step("Initialization")
    @Description("Setting web browser")
    @BeforeClass
    public void setUp() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver_win.exe");
        }
        if (os.contains("mac")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver_mac");
        } else {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver_linux");
        }

        String binary_mac_default = "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome";
        String binary_mac = "/Volumes/Transcend/Applications/Google Chrome.app/Contents/MacOS/Google Chrome";
        String binary_win = "C:\\Users\\%USERNAME%\\AppData\\Local\\Google\\ Chrome\\Application\\chrome.exe";
        String binary_lin = "/usr/bin/google-chrome";

        Configuration.browser = "chrome";
        if (Files.exists(Path.of(binary_mac_default))) {
            Configuration.browserBinary = binary_mac_default;
        } else if (Files.exists(Path.of(binary_mac))) {
            Configuration.browserBinary = binary_mac;
        } else if (Files.exists(Path.of(binary_win))) {
            Configuration.browserBinary = binary_win;
        } else if (Files.exists(Path.of(binary_lin))) {
            Configuration.browserBinary = binary_lin;
        }
        Configuration.driverManagerEnabled = true;
    }

    @Step("Quitting browser")
    @Description("Quitting web browser")
    @AfterClass
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
