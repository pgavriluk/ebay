package pages.factory;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static org.seleniumhq.jetty9.http.MultiPartParser.LOG;

public class SearchResultsPageFactory {

    WebDriver driver;

    @FindBy(className = "s-item")
    List<WebElement> allItems;

    @FindBy(className = "s-item__price")
    WebElement priceElement;

    @FindBy(className = "s-item__title")
    WebElement itemTitle;

    @FindBy(className = "s-item__shipping")
    WebElement shippingElement;

    @FindBy(className = "//div//span[contains(@class,'s-item__fnf')]")
    WebElement fastNFree;

    public SearchResultsPageFactory(WebDriver driver){
        this.driver =driver;
        PageFactory.initElements(driver, this);
    }

    public List<Double> getAllPricesByOrder() {
        LOG.info("Get all Total Prices by order");

        List<Double> prices = new ArrayList<>();
        String price = "";
        String shipping = "";
        Double totalPrice;

        //Get all items,find all prices and shipping costs and sum them
        for (WebElement item : allItems) {
            totalPrice = 0.00d;
            priceElement = item.findElement(By.className("s-item__price"));
            if (priceElement.getTagName().equals("span") && priceElement.getText().contains("$")) {
                price = priceElement.getText().replaceAll("[^\\d.]", "");
                totalPrice = Double.valueOf(price);
            } else {
                LOG.info("Can not find price for an item:" + itemTitle.getText());
            }

            //Try to get regular shipping or Free shipping
            try {
                shippingElement = item.findElement(By.className("s-item__shipping"));
            } catch (NoSuchElementException e) {
                //If does not exist try to find "FAST 'N FREE"
                shippingElement = item.findElement(By.xpath("//div//span[contains(@class,'s-item__fnf')]"));
            }

            if (shippingElement.getTagName().equals("span") && shippingElement.getText().contains("$")) {
                shipping = shippingElement.getText().replaceAll("[^\\d.]", "");
                totalPrice += Double.valueOf(shipping);
            }else if(shippingElement.getTagName().equals("span") && (!shippingElement.getText().contains("$") || shippingElement.getText().toLowerCase().contains("free"))) {
                //Free shipping, nothing to add, totalPrice stays totalPrice
                shipping = "0.00";
            }else {
                LOG.info("Can not find shipping for an item:" + itemTitle.getText());
            }

            prices.add(totalPrice);
        }
        return prices;
    }





}
