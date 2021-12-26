package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;

import java.nio.file.StandardWatchEventKinds;

public class CheckOutPage extends BasePage {

    private final By firstNameFld = By.cssSelector("input#billing_first_name");
    private final By lastNameFld = By.cssSelector("input#billing_last_name");
    private final By streetAddressFld = By.cssSelector("input#billing_address_1");
    private final By townFld = By.cssSelector("input#billing_city");
    private final By zipCodeFld = By.cssSelector("input#billing_postcode");
    private final By emailFld = By.cssSelector("input#billing_email");
    private final By placeOrderBtn = By.cssSelector("button#place_order");
    private final By successNotice = By.cssSelector("div.woocommerce-order >p");

    private final By showLoginLink = By.cssSelector(".showlogin");
    private final By userNameOrEmailFld = By.cssSelector("#username");
    private final By passwordFld = By.cssSelector("#password");
    private final By loginBtn = By.xpath("//button[@value=\"Login\"]");
    private final By overlay = By.cssSelector(".blockUI.blockOverlay");

    private final By stateDropDown = By.cssSelector("#billing_state");
    private final By countryDropDwon = By.cssSelector("#billing_country");
    private final By cashOnDeliveryRadioBtn = By.cssSelector("#payment_method_cod");
    private final By alternateuntryDropdown = By.id("select2-billing_country-container");
    private final By alternatStateDropdown = By.id("select2-billing_state-container");
    private final By productName = By.xpath("//td[@class='product-name']");

    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    public CheckOutPage load(){
        load("/checkout/");
        return this;
    }

    public CheckOutPage enterFirstNameFld(String fname){
        driver.findElement(firstNameFld).sendKeys(fname);
        return this;
    }

    public CheckOutPage enterLastNameFld(String lname){
        driver.findElement(lastNameFld).sendKeys(lname);
        return this;
    }

    public CheckOutPage enterAddressFld(String address){
        driver.findElement(streetAddressFld).clear();
        driver.findElement(streetAddressFld).sendKeys(address);
        return this;
    }

    public CheckOutPage enterTownFld(String town){
        driver.findElement(townFld).clear();
        driver.findElement(townFld).sendKeys(town);
        return this;
    }

    public CheckOutPage enterZipcodeFld(String zipcode){
        driver.findElement(zipCodeFld).clear();
        driver.findElement(zipCodeFld).sendKeys(zipcode);
        return this;
    }


    public CheckOutPage enterEmailFld(String email){
        driver.findElement(emailFld).clear();
        driver.findElement(emailFld).sendKeys(email);
        return this;
    }

    public CheckOutPage selectCountryDropdown(String contryName){

        wait.until(ExpectedConditions.visibilityOfElementLocated(alternateuntryDropdown)).click();
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='"+contryName+"']")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",e);
        e.click();
        /*Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(countryDropDwon)));
        select.selectByVisibleText(contryName);*/
        return this;
    }

    public CheckOutPage selectStateDropdown(String stateName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(alternatStateDropdown)).click();
        WebElement e1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='"+stateName+"']")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",e1);
        e1.click();
        /*Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(stateDropDown)));
        select.selectByVisibleText(stateName);*/
        return this;
    }

    public CheckOutPage setBillingAddress(BillingAddress billingAddress){
        return enterFirstNameFld(billingAddress.getFirstName())
                .enterLastNameFld(billingAddress.getLastName())
                .enterAddressFld(billingAddress.getAddressLineOne())
                .selectCountryDropdown(billingAddress.getCountry())
                .selectStateDropdown(billingAddress.getState())
                .enterTownFld(billingAddress.getCity())
                .enterZipcodeFld(billingAddress.getZipCode())
                .enterEmailFld(billingAddress.getEmail())
                .selectCashOnDeliveryRadioBtn();
    }

    public CheckOutPage selectCashOnDeliveryRadioBtn(){
        waitForOverlaytoDisappear(overlay);
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(cashOnDeliveryRadioBtn));
        if(!e.isSelected()){
            e.click();
        }
        return this;
    }

    public CheckOutPage clickPlaceOrderBtn() throws InterruptedException {
        waitForOverlaytoDisappear(overlay);
        driver.findElement(placeOrderBtn).click();
        return this;
    }

    public String getSuccessMessage(){
        return  wait.until(ExpectedConditions.visibilityOfElementLocated(successNotice)).getText();
    }

    public CheckOutPage clickHereToLoginLink(){
        driver.findElement(showLoginLink).click();
        return this;
    }

    public CheckOutPage enterUserName(String username) throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(userNameOrEmailFld).sendKeys(username);
        return this;
    }
    public CheckOutPage enterPassword(String password){
        driver.findElement(passwordFld).sendKeys(password);
        return this;
    }

    public CheckOutPage clickLoginBtn( ){
        driver.findElement(loginBtn).click();
        return this;
    }

    public CheckOutPage login(User user) throws InterruptedException {
        return enterUserName(user.getUserName()).enterPassword(user.getPassWord());

    }

    public String getProductName(){
        return  wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
    }
}
