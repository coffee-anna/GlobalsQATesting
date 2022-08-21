package Selenide.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ManagerPage {

    private final SelenideElement addCustomerBtn = $x("//button[@ng-class='btnClass1']");
    private final SelenideElement openAccountBtn = $x("//button[@ng-class='btnClass2']");
    private final SelenideElement customersBtn = $x("//button[@ng-class='btnClass3']");

    @Step("Redirecting to Add customer page")
    public AddCustomerPage goToAddCustomerPage() {
        addCustomerBtn.click();
        return new AddCustomerPage();
    }

    @Step("Redirecting to Open account page")
    public OpenAccountPage goToOpenAccountPage() {
        openAccountBtn.click();
        return new OpenAccountPage();
    }

    @Step("Redirecting to Customers page")
    public CustomersPage goToCustomersPage() {
        customersBtn.click();
        return new CustomersPage();
    }
}