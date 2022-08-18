package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tests.BaseInitializationTest;

import java.util.concurrent.TimeUnit;

public class MainPage extends BaseInitializationTest {

    WebDriver driver;
    String URL = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login";
    private WebElement loginPage;


    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(URL);
    }

    @Step("Login")
    public void LoginPage(String loginType) {
        // Login type choice
        loginPage = driver.findElement(By.xpath("//*[text()='"+loginType+"']"));
        loginPage.click();
    }

    @Step("Redirecting to home page")
    public HomePage goToHomePage() {
        return new HomePage(driver);
    }
}
