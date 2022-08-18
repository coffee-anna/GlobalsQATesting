package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
    WebDriver driver;

    @FindBy(xpath = "//span[@class='fontBig ng-binding']")
    WebElement curUsrName;

    @FindBy(xpath = "//*[text()='Logout']")
    WebElement logoutBtn;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Getting current user name")
    public String getCurUsrName() {
        return curUsrName.getText();
    }

    @Step("Redirecting to home page")
    public void goToHomePage() {
        logoutBtn.click();
    }
}
