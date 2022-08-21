package Selenide.tests;

import Selenide.pages.*;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Globalsqa.com test")
@Feature("Manager Login test")
public class ManagerLoginTest extends BaseInitializationTest{
    MainPage mainPage;
    ManagerPage managerPage;
    AddCustomerPage addCustomerPage;
    HomePage homePage;

    String[] newAccount = "Lord Voldemort RG12 9FG".split(" ", 3);
    String[] currencies = {"Dollar", "Pound", "Rupee"};


    @BeforeMethod
    @Description("Manager login testing")
    public void ManagerLogin() {
        mainPage = new MainPage();
        mainPage.goToManagerLoginPage();
        managerPage = mainPage.goToManagerPage();
    }

    @Test
    @Description("Customer addition testing")
    public void addCustomerTest() {

        // Adding new customer
        addCustomerPage = managerPage.goToAddCustomerPage();
        String[] alertMessages = addCustomerPage.addCustomer(newAccount[0], newAccount[1], newAccount[2]);

        addCustomerPage.goToMainPage().goToCustomerLoginPage();
        homePage = mainPage.goToHomePage();

        // Verifying alert message
        verifyAlertMessage(alertMessages[0], alertMessages[1]);

        // Verifying name of added customer
        String lastElement = homePage.getLastElement();
        verifyCustomerList(lastElement, "Lord Voldemort");

        addCustomerPage.goToMainPage();
    }

    private void verifyAlertMessage(String alertMessageActual, String alertMessageExpected) {
        Assert.assertTrue(alertMessageActual.contains(alertMessageExpected), "Unexpected alert message");
    }

    public void verifyCustomerList(String lastAddedUsr, String expectedName) {
        Assert.assertEquals(lastAddedUsr,
                expectedName, "Unexpected value");
    }

    @Test
    @Description("Account addition testing")
    public void openAccountTest() {

        // Adding account
        OpenAccountPage openAccountPage = managerPage.goToOpenAccountPage();
        int accountId = openAccountPage.addAccount(newAccount[0], newAccount[1], currencies[0]);

        // Redirecting to account page
        addCustomerPage.goToMainPage().goToCustomerLoginPage();
        homePage = mainPage.goToHomePage();
        homePage.UserSelectByIndex(homePage.getUsrListSize()-1);
        AccountPage accountPage = homePage.goToAccountPage();

        // Getting last edited account info
        ElementsCollection accountOptions = accountPage.getAccountOptions();
        ElementsCollection accountInfo = accountPage.getAccountInfo();

        System.out.println("last acc idx: "+accountId);
        verifyNewUserAccount(accountId, currencies[0], accountOptions, accountInfo);
    }

    public void verifyNewUserAccount(int expectedIdx, String expectedCurrency,
                                     ElementsCollection accountOptions, ElementsCollection accountInfo) {
        SelenideElement lastAddedAccount = accountOptions.get(accountOptions.size()-1);
        int actualIdx = Integer.parseInt(lastAddedAccount.getText());

        Assert.assertEquals(actualIdx,
                expectedIdx, "Unexpected index");
        Assert.assertEquals(accountInfo.get(accountInfo.size()-1).getText(),
                expectedCurrency, "Unexpected currency");
    }
}
