package TEST_LOGIC;

import WEB.Clicker;
import WEB.WebDrivers;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class ParentPriceRangeChecker {
    public static ArrayList<String> priceRangeDeviations(ArrayList<String> urlList){
        ArrayList<String> deviationList = new ArrayList<>();

        return null;
    }

    public static boolean isInPriceRange(ArrayList<Double> priceRange, Double price){
        //incert check of price range
        return price >= priceRange.get(0) && price <= priceRange.get(1);
    }

    public static ArrayList<String> compareParentPriceWithItsChildren (ArrayList<String> urlList){
        String parentUrl = urlList.get(0);
        ArrayList<String> deviationList = new ArrayList<>();
        ArrayList<String> normalList = new ArrayList<>();
        WebDriver driver = WebDrivers.firefoxDriver();
        Double parentPrice = Clicker.getParentPriceRange(driver, parentUrl).get(0);

        for (int i = 1; i < urlList.size();i++){
            WebDriver driverForChild = WebDrivers.firefoxDriver();
            Double childPrice = Clicker.getChildPrice(driverForChild, urlList.get(i));
            if (childPrice<parentPrice){
                deviationList.add(urlList.get(i));
            } else {
                normalList.add(urlList.get(i));
            }
            //driverForChild.close();
        }
        // if deviationList isn't empty, insert parent url for the first position
        if (deviationList.size()>0){
            deviationList.add(0, urlList.get(0));
        } else {
            deviationList.add(urlList.get(0));
            deviationList.add("parent's price is min of all of its children");
            for (String s:normalList) {
                deviationList.add(" / " + s);
            }
        }
        //driver.close();
        return deviationList;
    }
}
