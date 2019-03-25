package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class BasePage {


    private WebDriver driver;
    private WebDriverWait wait;

    protected final String BASE_URL = "https://www.ebay.com/";

    //Constructor
    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
    }

    protected void openUrl() {
        driver.get(BASE_URL);
    }

    private void waitVisibility(By elementBy) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
    }


    protected void click(By elementBy) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).click();
    }

    protected void writeText(By elementBy, String text) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).sendKeys(text);
    }

    protected String readText(By elementBy) {
        waitVisibility(elementBy);
        return driver.findElement(elementBy).getText();
    }

    protected WebElement findElement(By elementBy){
        return driver.findElement(elementBy);
    }

    protected void clickMenuAndClickOnElement(By menuElementBy, By clickElementBy){
        Actions action = new Actions(driver);
        WebElement menuElement = findElement(menuElementBy);
        action.moveToElement(menuElement).click().build().perform();

        WebElement clickElement = findElement(clickElementBy);

        action.moveToElement(clickElement).click().build().perform();
    }

    protected void hoverOnMenuAndClickOnElement (By menuElementBy, By clickElementBy) throws InterruptedException {
        Actions action = new Actions(driver);
        WebElement menuElement = findElement(menuElementBy);
        action.moveToElement(menuElement).build().perform();

        WebElement clickElement = findElement(clickElementBy);
        Thread.sleep(2000);
        action.moveToElement(clickElement).click().build().perform();
    }

    protected void scrollToElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected List<WebElement> findElements(By elementBy){
        return driver.findElements(elementBy);

    }

}
