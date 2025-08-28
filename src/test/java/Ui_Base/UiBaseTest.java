package Ui_Base;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UiBaseTest {
    protected static WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        System.setProperty("https://parabank.parasoft.com/parabank/index.htm", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
}
