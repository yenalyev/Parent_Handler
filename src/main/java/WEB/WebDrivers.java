package WEB;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDrivers {
    public static WebDriver firefoxDriver(){
        System.setProperty("webdriver.gecko.driver", "D:\\SOFTWARE\\JAVA\\geckodriver-v0.23.0-win64\\geckodriver.exe");
        FirefoxDriver driver = new FirefoxDriver();
        return driver;
    }
}
