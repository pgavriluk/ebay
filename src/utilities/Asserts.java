package utilities;

import org.junit.Assert;

import static org.seleniumhq.jetty9.http.MultiPartParser.LOG;

public abstract class Asserts {


    public static void assertEquals(String expectedString, String actualString){
        try {
            LOG.info("Compare two strings: Expected: " + expectedString + ", actual: " + actualString);
            Assert.assertEquals(expectedString, actualString);
        } catch (Exception e) {
            LOG.info("Comparison Failed", e);
            e.printStackTrace();
        }
    }

    public static void assertFalse(boolean condition){
        LOG.info("Assert False");
        Assert.assertFalse(condition);
    }


}
