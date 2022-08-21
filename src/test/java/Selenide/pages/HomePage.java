package Selenide.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;


public class HomePage {

    ElementsCollection usrList = $$x("//option");
    SelenideElement usrSelectOptions = $x("//*[@name='userSelect']");
    SelenideElement loginBtn = $x("//*[text()='Login']");

    public HomePage(){

        usrSelectOptions.shouldBe(Condition.visible);
        usrList.should(CollectionCondition.sizeGreaterThan(0));
    }

    @Step("Getting list of available users")
    public List<String> getUsrList() throws Exception {
        if (usrList.size() > 0) { usrList.remove(0);}    // Removing blank element
        else { throw new Exception("selenide empty list"); }
        return usrList.texts();
    }

    public int getUsrListSize() {
        return usrList.size();
    }

    @Step("Logging in as a user")
    public String UserSelectByIndex(int i) {
        Select usrSelect = new Select(usrSelectOptions);
        usrSelect.selectByIndex(i);
        return usrList.get(i).getText();
    }

    @Step("Getting last element of user list")
    public String getLastElement() {
        SelenideElement lastElement = usrList.get(usrList.size()-1);
        lastElement.click();
        return lastElement.getText();
    }

    @Step("Redirecting to account page")
    public AccountPage goToAccountPage() {
        loginBtn.click();
        return new AccountPage();
    }
}