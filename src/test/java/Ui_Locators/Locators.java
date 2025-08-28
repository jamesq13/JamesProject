package Ui_Locators;

import org.openqa.selenium.By;

public class Locators {
    // Registration page
    public static final By registerButton = By.xpath("//a[text()='Register']");
    public static final By firstName = By.xpath("//input[@id='customer.firstName']");
    public static final By lastName = By.xpath("//input[@id='customer.lastName']");
    public static final By adress = By.xpath("//input[@id='customer.address.street']");
    public static final By city = By.xpath("//input[@id='customer.address.city']");
    public static final By state = By.xpath("//input[@id='customer.address.state']");
    public static final By zip = By.xpath("//input[@id='customer.address.zipCode']");
    public static final By snn = By.xpath("//input[@id='customer.ssn']");
    public static final By userName = By.xpath("//input[@id='customer.username']");
    public static final By password = By.xpath("//input[@id='customer.password']");
    public static final By confirmPassword = By.xpath("//input[@id='repeatedPassword']");
    public static final By getRegister = By.xpath("//input[@value='Register']");
    // seccuse page
    public static final By succesMessage = By.xpath("//div/h1[text()='Welcome Bred123']");

}
