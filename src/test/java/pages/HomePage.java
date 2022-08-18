package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import tests.BaseInitializationTest;

import java.util.List;

public class HomePage extends BaseInitializationTest {

    WebDriver driver;

    @FindBy(tagName = "option")
    List<WebElement> usrList;

    @FindBy(name = "userSelect")
    WebElement usrSelectOptions;

    @FindBy(xpath = "//*[text()='Login']")
    WebElement loginBtn;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getUsrList() throws Exception {
        // List of available users
        if (usrList.size() > 0) {
            usrList.remove(0);  // Removing blank element
        } else {
            throw new Exception("Empty list");}
        return usrList;
    }

    @Step("Logging in as a user")
    public void UserLogin(int i) {
        Select usrSelect = new Select(usrSelectOptions);
        usrSelect.selectByIndex(i);
    }

    @Step("Redirecting to account page")
    public AccountPage goToAcc() {
        loginBtn.click();
        return new AccountPage(driver);
    }
}
