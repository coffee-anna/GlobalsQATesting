package Selenide.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$x;

public class AddCustomerPage {

    private final SelenideElement firstName = $x("//input[@placeholder=\"First Name\"]");
    private final SelenideElement lastName = $x("//input[@placeholder=\"Last Name\"]");
    private final SelenideElement postCode = $x("//input[@placeholder=\"Post Code\"]");
    private final SelenideElement submitBtn = $x("//*[@type='submit']");
    private final SelenideElement homeBtn = $x("//*[text()='Home']");

    @Step("Adding new customer")
    public String[] addCustomer(String firstNameVal, String lastNameVal, String postCodeVal) {
        String alertMessageExpected = "Customer added successfully";
        String alertMessageActual;

        // Adding new customer
        firstName.sendKeys(firstNameVal);
        lastName.sendKeys(lastNameVal);
        postCode.sendKeys(postCodeVal);
        submitBtn.click();

        // Getting alert message
        Alert alert = Selenide.switchTo().alert();
        alertMessageActual = alert.getText();

        alert.accept();
        return new String[]{alertMessageActual, alertMessageExpected};
    }

    public MainPage goToMainPage() {
        homeBtn.click();
        return new MainPage();
    }
}
