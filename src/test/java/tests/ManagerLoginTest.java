package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import pages.MainPage;

@Epic("Globalsqa.com test")
@Feature("Manager Login test")
public class ManagerLoginTest extends BaseInitializationTest{

    MainPage mainPage;
    String loginType = "Bank Manager Login";

    @Test
    @Description("Manager login testing")
    public void ManagerLogin() {
        mainPage = new MainPage(driver);
        mainPage.LoginPage(loginType);

    }
}
