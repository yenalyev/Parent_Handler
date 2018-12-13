package TEST_LOGIC;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ParentPriceRangeCheckerTest {
    ParentPriceRangeChecker parentPriceRangeCheckerObject = new ParentPriceRangeChecker();
    @Test
    void priceRangeDeviations() {
    }

    @Test
    void isInPriceRange() {
        ArrayList<Double> testPriceRangeList = new ArrayList<>();
        testPriceRangeList.add(20.22);
        testPriceRangeList.add(30.5);
        Double testPrice_1 = 0.0;
        Double testPrice_2 = -0.0;
        Double testPrice_3 = 30.0;
        Double testPrice_4 = 150.0;
        Double testPrice_5 = 20.22;
        Assert.assertFalse(parentPriceRangeCheckerObject.isInPriceRange(testPriceRangeList, testPrice_1));
        Assert.assertFalse(parentPriceRangeCheckerObject.isInPriceRange(testPriceRangeList, testPrice_2));
        Assert.assertTrue(parentPriceRangeCheckerObject.isInPriceRange(testPriceRangeList, testPrice_3));
        Assert.assertFalse(parentPriceRangeCheckerObject.isInPriceRange(testPriceRangeList, testPrice_4));
        Assert.assertTrue(parentPriceRangeCheckerObject.isInPriceRange(testPriceRangeList, testPrice_5));

    }
}