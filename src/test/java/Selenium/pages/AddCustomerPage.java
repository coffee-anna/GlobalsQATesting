package Selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class AddCustomerPage {

    WebDriver driver;

    @FindBy(xpath = "//input[@placeholder=\"First Name\"]")
    WebElement firstName;

    @FindBy(xpath = "//input[@placeholder=\"Last Name\"]")
    WebElement lastName;

    @FindBy(xpath = "//input[@placeholder=\"Post Code\"]")
    WebElement postCode;

    @FindBy(xpath = "//*[@type='submit']")
    WebElement submitBtn;

    @FindBy(xpath = "//*[text()='Home']")
    WebElement homeBtn;

    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Adding new customer")
    public void addCustomer(String firstNameVal, String lastNameVal, String postCodeVal) {
        String alertMessageExpected = "Customer added successfully";
        String alertMessageActual;

        // Adding new customer
        firstName.sendKeys(firstNameVal);
        lastName.sendKeys(lastNameVal);
        postCode.sendKeys(postCodeVal);
        submitBtn.click();

        // Getting alert message
        Alert alert = driver.switchTo().alert();
        alertMessageActual = alert.getText();
        System.out.println(alertMessageActual);

        ValidateNewCustomer(alertMessageActual, alertMessageExpected);
        alert.accept();
    }

    public void ValidateNewCustomer(String actual, String expected) {
        Assert.assertEquals(Boolean.TRUE,
                actual.contains(expected), "Unexpected text");
    }

    public MainPage goToMainPage() {
        homeBtn.click();
        return new MainPage(driver);
    }
}
