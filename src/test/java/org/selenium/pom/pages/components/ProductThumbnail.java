package org.selenium.pom.pages.components;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;

public class ProductThumbnail extends BasePage {

    private final By viewCartBtn =By.xpath("//a[@title=\"View cart\"]");

    public ProductThumbnail(WebDriver driver) {
        super(driver);
    }

    private By getAddToCartBtn(String productname){
        return By.xpath("//a[@aria-label=\"Add “"+productname+"” to your cart\"]");
    }

    @Step
    public ProductThumbnail clickAddToCartBtn(String productname){
        By addToCartBtn = getAddToCartBtn(productname);
        driver.findElement(addToCartBtn).click();
        return this;
    }

    @Step
    public CartPage clickViewCart(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewCartBtn)).click();
        // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(viewCartBtn));
        // driver.findElement(viewCartBtn).click();
        return new CartPage(driver);
    }
}
