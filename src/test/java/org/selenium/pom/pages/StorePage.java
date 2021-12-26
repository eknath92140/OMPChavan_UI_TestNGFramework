package org.selenium.pom.pages;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.components.ProductThumbnail;

public class StorePage extends BasePage {

    private final By searchFld = By.xpath("//input[@class=\"search-field\"]");
    private final By searchBtn= By.xpath("//*[@id=\"woocommerce_product_search-1\"]/form/button");
    private final By title = By.xpath("//h1[@class=\"woocommerce-products-header__title page-title\"]");

    private ProductThumbnail productThumbnail;


    public StorePage(WebDriver driver) {
        super(driver);
        productThumbnail= new ProductThumbnail(driver);
    }
    @Step
    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    private StorePage enterTextInSearchFld(String text) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchFld)).sendKeys(text);
        return this;
    }

    public StorePage search(String text) throws InterruptedException {
        enterTextInSearchFld(text).clickSearchBtn();
        return this;
    }

    private StorePage clickSearchBtn(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBtn)).click();
        return this;
    }

    public String getTitle(){
        return driver.findElement(title).getText();
    }

    @Step
    public StorePage load() {
        load("/store");
        return this;
    }
}
