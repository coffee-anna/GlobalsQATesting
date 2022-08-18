package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.HomePage;
import pages.MainPage;

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
            homePage.UserLogin(i);
            name = usrList.get(i).getText();
            System.out.println(name);
            accountPage = homePage.goToAcc();

            Assert.assertEquals(name,
                    accountPage.getCurUsrName(),
                    "Unexpected string");

            accountPage.goToHomePage();
        }

    }

}
