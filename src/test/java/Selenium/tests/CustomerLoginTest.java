package Selenium.tests;

import Selenium.pages.MainPage;
import Selenium.tests.BaseInitializationTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import Selenium.pages.AccountPage;
import Selenium.pages.HomePage;

import java.util.List;


@Epic("Globalsqa.com test")
@Feature("Customer Login test")
public class CustomerLoginTest extends BaseInitializationTest {
    String name;
    MainPage mainPage;
    HomePage homePage;
    AccountPage accountPage;
    String loginType = "Customer Login";

    @Test
    @Description("Customer login testing")
    public void CustomerLogin() throws Exception {
        mainPage = new MainPage(driver);
        mainPage.LoginPage(loginType);

        homePage = mainPage.goToHomePage();

        List<WebElement> usrList = homePage.getUsrList();
        for (int i = 1; i<usrList.size(); i++) {   // Parsing list of available users
            homePage.UserSelectByIndex(i);
            name = usrList.get(i).getText();
            accountPage = homePage.goToAcc();

            Assert.assertEquals(name,
                    accountPage.getCurUsrName(),
                    "Unexpected string");

            accountPage.goToHomePage();
        }
    }
}
