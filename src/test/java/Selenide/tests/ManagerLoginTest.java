package Selenide.tests;

import Selenide.pages.*;
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
    int accountId;


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
        accountId = openAccountPage.addAccount(newAccount[0], newAccount[1], currencies[0]);

        // Redirecting to account page
        addCustomerPage.goToMainPage().goToCustomerLoginPage();
        homePage = mainPage.goToHomePage();
        homePage.UserSelectByIndex(homePage.getUsrListSize()-1);
        AccountPage accountPage = homePage.goToAccountPage();

        // Verifying last edited account info
        verifyNewUserAccount(accountId, currencies[0],
                accountPage.getLastAddedAccountIdx(), accountPage.getLastAddedAccountCurrency());
    }

    public void verifyNewUserAccount(int expectedIdx, String expectedCurrency,
                                     String accountIdx, String accountCurrency) {
        Assert.assertEquals(accountIdx,
                String.valueOf(expectedIdx), "Unexpected index");
        Assert.assertEquals(accountCurrency,
                expectedCurrency, "Unexpected currency");
    }
}
