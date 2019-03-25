package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;

import static org.seleniumhq.jetty9.http.MultiPartParser.LOG;


public class SearchThroughMenuPage extends BasePage {


    public SearchThroughMenuPage(WebDriver driver) {
        super(driver);
    }

    public void openUrl() {
        LOG.info("Open Url: " + BASE_URL);
        super.openUrl();
    }

    public void clickShopByCategoryAndComputersAndTablets() {
        LOG.info("Open Shop By Category And then click Computers And Tablets");
        clickMenuAndClickOnElement(By.id("gh-shop-ei"), By.xpath("//a[contains(text(),'Computers & tablets')]"));
    }


    public void clickComputerDrivesStorageAndBlankMedia() {
        LOG.info("Click on Computer Drives Storage And Blank Media");
        click(By.xpath("/html/body/div[3]/div[4]/div[1]/div/div/div/section[1]/ul/li[4]/button/span"));
    }

    public void clickHardDrivesHddSsdAndNAS() {
        LOG.info("Click on Hard Drives HDD, SSD And NAS");
        click(By.xpath("//a[contains(text(),'Hard Drives (HDD, SSD & NAS)')]"));
    }

    public void clickExternalHardDiskDrives() {
        LOG.info("Click on External Hard Disk Drives");
        click(By.xpath("//a[contains(text(),'External Hard Disk Drives')]"));
    }

    public void click1Tb() {
        LOG.info("Choose 1TB");
        click(By.xpath("//div[@id='w1-w1-w0-w0-multiselect[6]']/a/input"));
    }

    public void clickUsb3() {
        LOG.info("Choose USB 3");
        click(By.xpath("//div[@id='w1-w1-w0-w0-multiselect[5]']/a/input"));
    }

    public void clickSortByPriceShippingLowestFirst() throws InterruptedException {
        LOG.info("Sort By Price Shipping Lowest First");

        //Perform Scroll on an application to the "Best Match"
        scrollToElement(findElement(By.xpath("//div[contains(text(),'Best Match')]")));

        //Open Best Match and choose "Price + Shipping: lowest first"
        hoverOnMenuAndClickOnElement(By.xpath("//div[contains(text(),'Best Match')]"), By.xpath("//span[contains(text(),'Price + Shipping: lowest first')]"));
    }

    public String readShopByCategoryText() {
        LOG.info("Reading Shop by category text");
        return readText(By.xpath("//*[@id=\"gh-shop-a\"]"));
    }

    public String readSelectionText() {
        LOG.info("Reading Selection text");
        return readText(By.xpath("/html/body/div[3]/div[2]/h1/span"));
    }

    public String readSortByText() {
        LOG.info("Reading Sort By text");
        return readText(By.xpath("//*[@id=\"w5-w0-w1\"]/button/div/div"));
    }

}
