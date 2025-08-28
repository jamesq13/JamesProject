package Ui_Tests;

import Ui_Base.UiBaseTest;
import Ui_Locators.Locators;
import config.TestConfig;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterTest extends UiBaseTest {
    @Test
    public void register() {
        driver.get(TestConfig.BASE_URL2);
        driver.findElement(Locators.registerButton).click();
        driver.findElement(Locators.firstName).sendKeys("Bred");
        driver.findElement(Locators.lastName).sendKeys("Pitt");
        driver.findElement(Locators.adress).sendKeys("123 Main St");
        driver.findElement(Locators.city).sendKeys("Tashkent");
        driver.findElement(Locators.state).sendKeys("Kazakhstan");
        driver.findElement(Locators.zip).sendKeys("12345");
        driver.findElement(Locators.snn).sendKeys("123123");
        driver.findElement(Locators.userName).sendKeys("Bred123");
        driver.findElement(Locators.password).sendKeys("zxc12345");
        driver.findElement(Locators.confirmPassword).sendKeys("zxc12345");
        driver.findElement(Locators.getRegister).click();
        WebElement messageElement = driver.findElement(Locators.succesMessage);
        assertEquals("Welcome Bred123", messageElement.getText());
    }
}