package WEB;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ClickerTest {
    Clicker clickerObject = new Clicker();
    @Test
    void getParentPriceRange() {
        WebDrivers webDriver = new WebDrivers();
        ArrayList<Double> priceRangeIntegerArr = new ArrayList<>();
        priceRangeIntegerArr = clickerObject.getParentPriceRange(webDriver.firefoxDriver(), "https://www.carid.com/konig-wheels/backbone-matte-black-milled-logo-on-spoke-465240.html");
        System.out.println(priceRangeIntegerArr);
    }

    @Test
    void isItPriceRange(){
       Assert.assertFalse(clickerObject.isItPriceRange("$86.18"));
       Assert.assertTrue(clickerObject.isItPriceRange("$86.18 - $86.18"));
    }

    @Test
    void getChildPriceTest(){
        WebDrivers webDriver = new WebDrivers();
        Double result = clickerObject.getChildPrice(webDriver.firefoxDriver(), "https://www.carid.com/konig-wheels/helium-he65d0440s-b-465416.html");
        System.out.println(result);
        Assert.assertEquals(80.55, result, 0.0);
    }

    @Test
    void getChildIdTest(){
        WebDrivers webDriver = new WebDrivers();
        System.out.println(clickerObject.getChildId(webDriver.firefoxDriver(), "https://www.carid.com/konig-wheels/helium-he65d0440s-b-465416.html"));
    }


}