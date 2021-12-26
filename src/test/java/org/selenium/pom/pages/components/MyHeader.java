package org.selenium.pom.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.StorePage;

public class MyHeader extends BasePage {

    private final By storMenuLink=By.xpath("//*[@id=\"menu-item-1227\"]/a");

    public MyHeader(WebDriver driver) {
        super(driver);
    }

    public StorePage clickStoreMenuLink(){

        driver.findElement(storMenuLink).click();
        return new StorePage(driver);
        // if we are sure that after clicking this button we get storpage then we can return its object and its called fluent interface
        // this can cause tight coupling, so some people prefer to craete page objects from test case class
    }
}
