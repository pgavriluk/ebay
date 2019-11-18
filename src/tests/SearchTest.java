package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.SearchResultsPage;
import pages.SearchThroughMenuPage;
import pages.factory.SearchResultsPageFactory;
import pages.factory.SearchThroughMenuPageFactory;
import utilities.Asserts;

import java.util.List;

import static org.seleniumhq.jetty9.http.MultiPartParser.LOG;

public class SearchTest extends BaseTest {

    private SearchThroughMenuPage searchThroughMenuPage;
    private SearchResultsPage searchResultsPage;
    private SearchThroughMenuPageFactory searchThroughMenuPageFactory;
    private SearchResultsPageFactory searchResultsPageFactory;


    @BeforeEach
    public void init() {
        LOG.info("Starting Init");
        super.init();
        searchThroughMenuPage = new SearchThroughMenuPage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        searchThroughMenuPageFactory = new SearchThroughMenuPageFactory(driver);
        searchResultsPageFactory = new SearchResultsPageFactory(driver);
    }

    @Test
    public void searchThroughMenuAndSortResultsTest() throws InterruptedException {
        try {
            LOG.info("Starting Search Through Menu And Sort Results Test");
            Asserts.assertEquals("Shop by category",searchThroughMenuPage.readShopByCategoryText());
            searchThroughMenuPage.clickShopByCategoryAndComputersAndTablets();
            Asserts.assertEquals("Computers, Tablets & More",searchThroughMenuPage.readSelectionText());
            searchThroughMenuPage.clickComputerDrivesStorageAndBlankMedia();
            searchThroughMenuPage.clickHardDrivesHddSsdAndNAS();
            Asserts.assertEquals("Hard Drives (HDD, SSD & NAS)", searchThroughMenuPage.readSelectionText());
            searchThroughMenuPage.clickExternalHardDiskDrives();
            searchThroughMenuPage.click1Tb();
            searchThroughMenuPage.clickUsb3();
            Asserts.assertEquals("1TB USB 3.0 Computer External Hard Disk Drives", searchThroughMenuPage.readSelectionText());
            searchThroughMenuPage.clickSortByPriceShippingLowestFirst();
            Asserts.assertEquals("Price + Shipping: lowest first", searchThroughMenuPage.readSortByText());

            List<Double> prices = searchResultsPage.getAllPricesByOrder();

            for(int i = 0; i<prices.size()-1; i++){
                LOG.info("Check if price + shipping '$" + prices.get(i) + "' smaller than next price + shipping '$" + prices.get(i+1) + "'");
                Asserts.assertFalse(prices.get(i) > prices.get(i+1));
            }

            LOG.info("Ending Search Through Menu And Sort Results Test - Status SUCCESS" );
        } catch (Exception e) {
            LOG.info(e);
            throw e;
        }


    }
    @Test
    public void searchThroughMenuAndSortResultsUsingFactoryTest() throws InterruptedException {
        try {
            LOG.info("Starting Search Through Menu And Sort Results Using Page Factory Test");
            Asserts.assertEquals("Shop by category",searchThroughMenuPageFactory.readShopByCategoryText());
            searchThroughMenuPageFactory.clickShopByCategoryAndComputersAndTablets();
            Asserts.assertEquals("Computers, Tablets & More",searchThroughMenuPageFactory.readSelectionText());
            searchThroughMenuPageFactory.clickComputerDrivesStorageAndBlankMedia();
            searchThroughMenuPageFactory.clickHardDrivesHddSsdAndNAS();
            Asserts.assertEquals("Hard Drives (HDD, SSD & NAS)", searchThroughMenuPageFactory.readSelectionText());
            searchThroughMenuPageFactory.clickExternalHardDiskDrives();
            searchThroughMenuPageFactory.click1Tb();
            searchThroughMenuPageFactory.clickUsb3();
            Asserts.assertEquals("1TB USB 3.0 Computer External Hard Disk Drives", searchThroughMenuPageFactory.readSelectionText());
            searchThroughMenuPageFactory.clickSortByPriceShippingLowestFirst();
            Asserts.assertEquals("Price + Shipping: lowest first", searchThroughMenuPageFactory.readSortByText());

            List<Double> prices = searchResultsPageFactory.getAllPricesByOrder();

            for(int i = 0; i<prices.size()-1; i++){
                LOG.info("Check if price + shipping '$" + prices.get(i) + "' smaller than next price + shipping '$" + prices.get(i+1) + "'");
                Asserts.assertFalse(prices.get(i) > prices.get(i+1));
            }

            LOG.info("Ending Search Through Menu And Sort Results Using Page Factory Test - Status SUCCESS" );
        } catch (Exception e) {
            LOG.info(e);
            throw e;
        }


    }


    @AfterEach
    public void tearDown() throws Exception {
        LOG.info("End of Test Suite");
        super.teardown();
    }
}
