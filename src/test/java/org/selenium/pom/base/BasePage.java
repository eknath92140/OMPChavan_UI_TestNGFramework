package org.selenium.pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.utils.ConfigLoader;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver=driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        //longwait = new WebDriverWait(driver, Duration.ofSeconds(20));// if u want u can create 2-3 types of wait aving duration and an be use anywhere in tests
    }

    public void load(String endpoint){
        driver.get(ConfigLoader.getInstance().getPropertyValue("baseUrl") +endpoint);
    }

    public void waitForOverlaytoDisappear(By overlay){

        List<WebElement> overlays = driver.findElements(overlay);
        System.out.println("Overlay count before = "+ overlays.size());

        if(overlays.size() > 0){
                new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                        ExpectedConditions.invisibilityOfAllElements(overlays)
                );
            System.out.println("Overlay is invisible ");
        }else{
            System.out.println("Overlay Not found");
        }
    }
}
