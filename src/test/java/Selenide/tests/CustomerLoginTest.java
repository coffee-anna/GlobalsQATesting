package Selenide.tests;

import Selenide.pages.AccountPage;
import Selenide.pages.HomePage;
import Selenide.pages.MainPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;


@Epic("Globalsqa.com test")
@Feature("Customer Login test")
public class CustomerLoginTest extends BaseInitializationTest{
    String name;
    MainPage mainPage;
    HomePage homePage;
    AccountPage accountPage;

    @Test
    @Description("Customer login testing")
    public void CustomerLogin() {
        mainPage = new MainPage();
        mainPage.goToCustomerLoginPage();
        homePage = mainPage.goToHomePage();

        int usrListSize = homePage.getUsrListSize(); // Getting number of available users
        for (int i = 1; i<usrListSize; i++) {   //Parsing list of available users
            name = homePage.UserSelectByIndex(i);
            accountPage = homePage.goToAccountPage();

            Assert.assertEquals(name,
                    accountPage.getCurUsrName(),
                    "Unexpected string");

            accountPage.goToHomePage();
        }
    }

}
