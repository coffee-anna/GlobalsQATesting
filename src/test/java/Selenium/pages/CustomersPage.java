package Selenium.pages;

import Selenium.tests.BaseInitializationTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CustomersPage extends BaseInitializationTest {

    WebDriver driver;

    public CustomersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
