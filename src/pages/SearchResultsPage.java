package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.seleniumhq.jetty9.http.MultiPartParser.LOG;

public class SearchResultsPage extends BasePage {

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }


    public List<Double> getAllPricesByOrder() {
        LOG.info("Get all Total Prices by order");

        List<Double> prices = new ArrayList<>();
        String price = "";
        String shipping = "";
        Double totalPrice;
        List<WebElement> items;
        WebElement priceElement;
        WebElement shippingElement;

        //Get all items
        items = findElements(By.className("s-item"));

        //Find all prices and shipping costs and sum them
        for (WebElement item : items) {
            priceElement = item.findElement(By.className("s-item__price"));
            totalPrice = 0.00d;
            if (priceElement.getTagName().equals("span") && priceElement.getText().contains("$")) {
                price = priceElement.getText().replaceAll("[^\\d.]", "");
                totalPrice = Double.valueOf(price);
            } else {
                LOG.info("Can not find price for an item:" + item.findElement(By.className("s-item__title")).getText());
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
                LOG.info("Can not find shipping for an item:" + item.findElement(By.className("s-item__title")).getText());
            }

            prices.add(totalPrice);
        }
        return prices;
    }

}
