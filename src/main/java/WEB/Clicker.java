package WEB;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class Clicker {
    public static void goToProductByUrl( WebDriver driver, String url){
        //WebDriver driver = WebDrivers.firefoxDriver();
        driver.get(url);
    }

    public static ArrayList<Double> getParentPriceRange(WebDriver driver, String url){
        ArrayList<Double> priceRangeDoubleArr = new ArrayList<>();
        driver.get(url);
        WebElement priceInfo = driver.findElement(By.cssSelector("div.prod-price-h > span > span"));
        String priceFromPage = priceInfo.getText();
        if (isItPriceRange(priceFromPage)){
            String[] splitPriceRange = priceFromPage.split(" - ");
            priceRangeDoubleArr.add(Double.parseDouble(splitPriceRange[0].replaceAll("\\$", "")));
            priceRangeDoubleArr.add(Double.parseDouble(splitPriceRange[1].replaceAll("\\$", "")));

        } else {
            priceRangeDoubleArr.add(Double.parseDouble(priceFromPage.replaceAll("\\$", "")));
            priceRangeDoubleArr.add(Double.parseDouble(priceFromPage.replaceAll("\\$", "")));
        }
        driver.close();
        return priceRangeDoubleArr;
    }

    public static boolean isItPriceRange(String priceInfo){
        return priceInfo.matches("\\$\\d*\\.\\d*\\s-\\s\\$\\d*\\.\\d*");
    }

    public static Double getChildPrice(WebDriver driver, String url){
        driver.get(url);
        WebElement priceInfo = driver.findElement(By.cssSelector("div.prod-price-h > span > span"));
        String priceFromPage = priceInfo.getText().replaceAll("\\$", "");
        Double price = Double.parseDouble(priceFromPage);
        driver.close();
        return price;
    }

    public static String getChildId(WebDriver driver, String url){
        driver.get(url);
        WebElement id = driver.findElement(By.xpath("/html/body/div[7]/div[1]/div/main/div[3]/div[1]/form/div[1]/div/span/span[2]"));
        return id.getText();
    }


}
