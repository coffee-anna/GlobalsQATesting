package Selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;


public class HomePage {

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

    @Step("Getting list of available users")
    public List<WebElement> getUsrList() throws Exception {
        // List of available users
        if (usrList.size() > 0) {
            usrList.remove(0);  // Removing blank element
        } else {
            throw new Exception("selenium empty list");}
        return usrList;
    }

    public int getUsrListSize() {
        return usrList.size();
    }

    @Step("Logging in as a user")
    public void UserSelectByIndex(int i) {
        Select usrSelect = new Select(usrSelectOptions);
        usrSelect.selectByIndex(i);
    }

    @Step("Getting last element of user list")
    public void GetLastElement() {
        usrList.get(usrList.size()-1).click();
    }

    @Step("Getting last element of user list")
    public WebElement GetLastElement(List<WebElement> usrList) {
        WebElement lastElement = usrList.get(usrList.size()-1);
        lastElement.click();
        return lastElement;
    }

    @Step("Redirecting to account page")
    public AccountPage goToAcc() {
        loginBtn.click();
        return new AccountPage(driver);
    }

    @Step("Redirecting to main page")
    public MainPage goToMainPage() {
        $(By.xpath("//*[text()='Home']")).click();
        return new MainPage(driver);
    }
}