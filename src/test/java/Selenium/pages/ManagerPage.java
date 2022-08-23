package Selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ManagerPage {

    WebDriver driver;

    @FindBy(xpath = "//button[@ng-class='btnClass1']")
    WebElement addCustomerBtn;

    @FindBy(xpath = "//button[@ng-class='btnClass2']")
    WebElement openAccountBtn;

    @FindBy(xpath = "//button[@ng-class='btnClass3']")
    WebElement customersBtn;

    public ManagerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Redirecting to Add customer page")
    public AddCustomerPage goToAddCustomerPage() {
        addCustomerBtn.click();
        return new AddCustomerPage(driver);
    }

    @Step("Redirecting to Open account page")
    public OpenAccountPage goToOpenAccountPage() {
        openAccountBtn.click();
        return new OpenAccountPage(driver);
    }
}