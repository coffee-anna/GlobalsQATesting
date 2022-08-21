package Selenium.pages;

import Selenium.tests.BaseInitializationTest;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class MainPage extends BaseInitializationTest {

    WebDriver driver;
    String URL = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login";
    private WebElement loginPage;


    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Step("Login")
    public void LoginPage(String loginType) {
        // Login type choice aka Seleniumers
        loginPage = driver.findElement(By.xpath("//*[text()='"+loginType+"']"));
        loginPage.click();
    }

    @Step("Redirecting to home page")
    public HomePage goToHomePage() {return new HomePage(driver);}

    @Step("Redirecting to manager page")
    public ManagerPage goToManagerPage() {return new ManagerPage(driver);}
}
