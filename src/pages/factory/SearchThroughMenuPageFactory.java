package pages.factory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchThroughMenuPageFactory {

    WebDriver driver;

    @FindBy(id="gh-shop-ei")
    WebElement shopByCategoryMenu;

    @FindBy(xpath="//a[contains(text(),'Computers & tablets')]")
    WebElement computersAndTabletsMenu;

    @FindBy(xpath="/html/body/div[3]/div[4]/div[1]/div/div/div/section[1]/ul/li[4]/button/span")
    WebElement computerDrivesStorageAndBlankMedia;

    @FindBy(xpath="//a[contains(text(),'Hard Drives (HDD, SSD & NAS)')]")
    WebElement hardDrivesHddSsdAndNas;

    @FindBy(xpath="//a[contains(text(),'External Hard Disk Drives')]")
    WebElement externalHardDiskDrives;

    @FindBy(xpath="//div[@id='w1-w1-w0-w0-multiselect[6]']/a/input")
    WebElement oneTb;

    @FindBy(xpath="//div[@id='w1-w1-w0-w0-multiselect[5]']/a/input")
    WebElement usb3;

    @FindBy(xpath="//div[contains(text(),'Best Match')]")
    WebElement bestMatch;

    @FindBy(xpath="//span[contains(text(),'Price + Shipping: lowest first')]")
    WebElement priceShippingLowestFirst;

    @FindBy(xpath="//*[@id=\"gh-shop-a\"]")
    WebElement shopByCategoryText;

    @FindBy(xpath="/html/body/div[3]/div[2]/h1/span")
    WebElement selectionText;

    @FindBy(xpath="//*[@id=\"w5-w0-w1\"]/button/div/div")
    WebElement sortByText;


    public SearchThroughMenuPageFactory(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickShopByCategoryAndComputersAndTablets(){
        Actions action = new Actions(driver);
        action.moveToElement(shopByCategoryMenu).click().build().perform();

        action.moveToElement(computersAndTabletsMenu).click().build().perform();
    }

    public void clickComputerDrivesStorageAndBlankMedia(){
        computerDrivesStorageAndBlankMedia.click();
    }

    public void clickHardDrivesHddSsdAndNAS(){
        hardDrivesHddSsdAndNas.click();
    }

    public void clickExternalHardDiskDrives(){
        externalHardDiskDrives.click();
    }

    public void click1Tb(){
        oneTb.click();
    }

    public void clickUsb3(){
        usb3.click();
    }

    public void clickSortByPriceShippingLowestFirst() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView(true);", bestMatch);


        Actions action = new Actions(driver);
        action.moveToElement(bestMatch).build().perform();

        Thread.sleep(2000);
        action.moveToElement(priceShippingLowestFirst).click().build().perform();
    }


    public String readShopByCategoryText(){
        return shopByCategoryText.getText();
    }

    public String readSelectionText(){
        return selectionText.getText();
    }

    public String readSortByText(){
        return sortByText.getText();
    }

}
