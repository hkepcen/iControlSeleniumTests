package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    public static WebDriver open(String type) {

        if (type.equals("chrome")) {
            String driverPath = "chromedriver-mac";

            System.setProperty("webdriver.chrome.driver", driverPath);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors");
            return new ChromeDriver();
        } else {
            throw new IllegalArgumentException("Chrome is the only valid driver");
        }

    }

}
