package Selenide.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class OpenAccountPage {

    private final SelenideElement userSelect = $("#userSelect");
    private final SelenideElement currencySelect = $("#currency");
    private final SelenideElement submitBtn = $x("//button[@type='submit']");
    private final SelenideElement homeBtn = $x("//*[text()='Home']");

    @Step("Adding new account")
    public int addAccount(String firstNameVal, String lastNameVal, String currency) {
        String user = firstNameVal + ' ' + lastNameVal;
        String alertMessageExpected = "Account created successfully";
        String alertMessageActual;

        userSelect.click();
       $x("//*[text()='"+user+"']").click();

        currencySelect.click();
        $x("//option[@value='"+currency+"']").click();

        submitBtn.click();

        Alert alert = Selenide.switchTo().alert();
        alertMessageActual = alert.getText();
        Assert.assertEquals(Boolean.TRUE,
                alertMessageActual.contains(alertMessageExpected), "Unexpected alert");

        alert.accept();
        return Integer.parseInt(alertMessageActual.replaceAll("[\\D]", ""));
    }
}
