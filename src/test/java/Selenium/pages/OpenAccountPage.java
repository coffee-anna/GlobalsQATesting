package Selenium.pages;

import Selenium.tests.BaseInitializationTest;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class OpenAccountPage extends BaseInitializationTest {

    WebDriver driver;

    @FindBy(id = "userSelect")
    WebElement userSelect;

    @FindBy(id = "currency")
    WebElement currencySelect;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitBtn;

    @FindBy(xpath = "//*[text()='Home']")
    WebElement homeBtn;

    public OpenAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Adding new account")
    public int addAccount(String firstNameVal, String lastNameVal, String currency) {
        String user = firstNameVal + ' ' + lastNameVal;
        String alertMessageActual;

        userSelect.click();
        driver.findElement(By.xpath("//*[text()='"+user+"']")).click();

        currencySelect.click();
        driver.findElement(By.xpath("//option[@value='"+currency+"']")).click();

        submitBtn.click();

        Alert alert = driver.switchTo().alert();
        alertMessageActual = alert.getText();
        Assert.assertEquals(Boolean.TRUE,
                alertMessageActual.contains("Account created successfully"), "Unexpected alert");

        alert.accept();
        return Integer.parseInt(alertMessageActual.replaceAll("[\\D]", ""));
    }

    @Step("Redirecting to Home page")
    public MainPage goToMainPage() {
        homeBtn.click();
        return new MainPage(driver);
    }
}
