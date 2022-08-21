package Selenium.tests;

import Selenium.pages.*;
import Selenium.tests.BaseInitializationTest;
import Selenium.pages.*;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@Epic("Globalsqa.com test")
@Feature("Manager Login test")
public class ManagerLoginTest extends BaseInitializationTest {
    MainPage mainPage;
    ManagerPage managerPage;
    AddCustomerPage addCustomerPage;
    OpenAccountPage openAccountPage;
    HomePage homePage;
    AccountPage accountPage;
    WebElement lastElement;
    String loginType = "Bank Manager Login";

    String[] newAccount = "Lord Voldemort RG12 9FG".split(" ", 3);
    String[] currencies = {"Dollar", "Pound", "Rupee"};

    @BeforeMethod
    @Description("Manager login testing")
    public void ManagerLogin() {
        mainPage = new MainPage(driver);
        mainPage.LoginPage(loginType);
        System.out.println("manager login");
        managerPage = mainPage.goToManagerPage();
    }

    @Test
    @Description("Customer addition testing")
    public void AddCustomerPageTest() throws Exception {

        addCustomerPage = managerPage.goToAddCustomerPage();
        addCustomerPage.addCustomer(newAccount[0], newAccount[1], newAccount[2]);

        mainPage = addCustomerPage.goToMainPage();
        mainPage.LoginPage("Customer Login");
        homePage = mainPage.goToHomePage();

        lastElement = homePage.GetLastElement(homePage.getUsrList());
        ValidateCustomerList(lastElement, "Lord Voldemort");

        addCustomerPage.goToMainPage();
    }

    public void ValidateCustomerList(WebElement lastAddedUsr, String expectedName) {
        Assert.assertEquals(lastAddedUsr.getText(),
                expectedName, "Unexpected value");
    }

    @Test
    @Description("Account addition testing")
    public void OpenAccountTest() {

        openAccountPage = managerPage.goToOpenAccountPage();

        int accountId = openAccountPage.addAccount(newAccount[0], newAccount[1], currencies[0]);

        mainPage = addCustomerPage.goToMainPage();
        mainPage.LoginPage("Customer Login");
        homePage = mainPage.goToHomePage();

        homePage.UserSelectByIndex(homePage.getUsrListSize()-1);

        accountPage = homePage.goToAcc();
        System.out.println("account page");
        accountPage.verifyNewUserAccount(accountId, currencies[0]);
    }
}
