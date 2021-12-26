package org.selenium.pom.factory.abstractFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.selenium.pom.factory.DriverManager;

import java.time.Duration;

public class ChromeDriverManagerAbstract extends  DriverManagerAbstract {

    @Override
    protected void startDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();  // here we are using driver declared in "DriverManagerAbstract" abstract class
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
}
