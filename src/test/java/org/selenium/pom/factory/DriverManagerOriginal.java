package org.selenium.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.selenium.pom.constants.DriverType;

import java.time.Duration;

import static org.selenium.pom.constants.DriverType.*;

public class DriverManagerOriginal {

    public WebDriver initializeDriver(String localBrowser){
        WebDriver driver;

        switch (DriverType.valueOf(localBrowser)){

            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case FIREFOX:
                 WebDriverManager.firefoxdriver().cachePath("Driver").setup();
                 driver = new FirefoxDriver();
                 break;

            default : throw new IllegalStateException("Invalid browser name provided : "+localBrowser);
        }


        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;
    }
}
