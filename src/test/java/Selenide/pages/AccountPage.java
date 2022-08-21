package Selenide.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class AccountPage {

    private final SelenideElement curUsrName = $x("//span[@class='fontBig ng-binding']");
    private final SelenideElement logoutBtn = $x("//*[text()='Logout']");
    private final ElementsCollection accountInfo = $$x("//strong[@class='ng-binding']");
    private final ElementsCollection accountOptions = $$x("//option[@label]");

    @Step("Getting current user name")
    public String getCurUsrName() { return curUsrName.getText();}

    @Step("Redirecting to home page")
    public void goToHomePage() { logoutBtn.click();}

    public ElementsCollection getAccountInfo() { return accountInfo;}

    public ElementsCollection getAccountOptions() { return accountOptions;}
}