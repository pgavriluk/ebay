package tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {


    protected static WebDriver driver;
    final String BASE_URL = "https://www.ebay.com/";

    @BeforeClass
    public void init () {
        //Gecko driver for FireFox browser
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver");
        //Create a FireFox driver. All test classes will use this.
        driver = new FirefoxDriver();

        //Maximize Window
        driver.manage().window().maximize();
        //Implicit wait set to 15 seconds
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(BASE_URL);
    }

    @AfterClass
    public void teardown () {
        driver.quit();
    }
}
