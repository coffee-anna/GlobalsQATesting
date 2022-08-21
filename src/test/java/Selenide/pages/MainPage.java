package Selenide.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;


public class MainPage {

    String URL = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login";

    public MainPage() {
        open(URL);
    }

    @Step("Customer login")
    public void goToCustomerLoginPage() {
        $x("//*[text()='Customer Login']").click();
    }

    @Step("Manager login")
    public void goToManagerLoginPage() {
        $x("//*[text()='Bank Manager Login']").click();
    }

    @Step("Redirecting to home page")
    public HomePage goToHomePage() {return new HomePage();}

    @Step("Redirecting to  page")
    public ManagerPage goToManagerPage() {return new ManagerPage();}
}
