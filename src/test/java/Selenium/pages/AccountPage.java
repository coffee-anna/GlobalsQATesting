package Selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class AccountPage {
    WebDriver driver;

    @FindBy(xpath = "//span[@class='fontBig ng-binding']")
    WebElement curUsrName;

    @FindBy(xpath = "//*[text()='Logout']")
    WebElement logoutBtn;

    @FindBy(xpath = "//option[@label]")
    List<WebElement> accountOptions;

    @FindBy(xpath = "//strong[@class='ng-binding']")
    List<WebElement> accountInfo;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Getting current user name")
    public String getCurUsrName() {
        return curUsrName.getText();
    }

    @Step("Verifying new user account")
    public void verifyNewUserAccount(int expectedIdx, String expectedCurrency) {
        WebElement lastAddedAccount = accountOptions.get(accountOptions.size()-1);
        int actualIdx = Integer.parseInt(lastAddedAccount.getText());

        Assert.assertEquals(actualIdx,
                expectedIdx, "Unexpected index");
        Assert.assertEquals(accountInfo.get(accountInfo.size()-1).getText(),
                expectedCurrency, "Unexpected currency");
    }

    @Step("Redirecting to home page")
    public void goToHomePage() {
        logoutBtn.click();
    }
}