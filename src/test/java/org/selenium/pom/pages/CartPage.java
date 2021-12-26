package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;

public class CartPage extends BasePage {

    private final By getProductName = By.xpath("//td[@class='product-name']/a");
    private final By proceedToheckOutBtn = By.xpath("//div[@class='wc-proceed-to-checkout']/a");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName(){
        return driver.findElement(getProductName).getText();
    }

    public CheckOutPage clickProceedToCheckOutBtn(){
        driver.findElement(proceedToheckOutBtn).click();
        return new CheckOutPage(driver);
    }

}
