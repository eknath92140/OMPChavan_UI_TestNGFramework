package org.selenium.pom.tests;


import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckOutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyFirstTestCase extends BaseTest {

 /*   @Test
    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {


        BillingAddress billingAddress= JacksonUtils.deserializeJSON("myBillingAddress.json",BillingAddress.class);

        Product product = new Product(1215);

        StorePage storePage = new HomePage(getDriver())
                .load()
                .clickStoreMenuLink().search("Blue");     // to make method redble we can mention "navigateToStoreUsingMenu()"

        Thread.sleep(5000);
        Assert.assertTrue(storePage.getTitle().contains("Search results:"));
      //  Thread.sleep(3000);
        storePage.clickAddToCartBtn(product.getName());
       // Thread.sleep(10000);

        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(),product.getName());

       CheckOutPage checkOutPage=  cartPage
               .clickProceedToCheckOutBtn()
               .setBillingAddress(billingAddress)
               .clickPlaceOrderBtn();


       // Thread.sleep(5000);
        Assert.assertEquals(checkOutPage.getSuccessMessage(),"Thank you. Your order has been received.");

    }*/

   // @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {

        Product product = new Product(1215);
        BillingAddress billingAddress= JacksonUtils.deserializeJSON("myBillingAddress.json",BillingAddress.class);
        User user= new User(ConfigLoader.getInstance().getPropertyValue("username"),ConfigLoader.getInstance().getPropertyValue("password"));

        StorePage storePage = new HomePage(getDriver())
                .load()
                .getMyHeader()
                .clickStoreMenuLink().search("Blue");

        Thread.sleep(3000);
        Assert.assertTrue(storePage.getTitle().contains("Search results:"));
        storePage
                .getProductThumbnail()
                .clickAddToCartBtn("Blue Shoes");

        Thread.sleep(10000);

        CartPage cartPage = storePage
                .getProductThumbnail()
                .clickViewCart();
        Assert.assertEquals(cartPage.getProductName(),product.getName());


        CheckOutPage checkOutPage=  cartPage.clickProceedToCheckOutBtn();
        checkOutPage
                .clickHereToLoginLink()
                .login(user)
                .clickLoginBtn();

        checkOutPage
                .setBillingAddress(billingAddress)
                .clickPlaceOrderBtn();

        Thread.sleep(5000);
        Assert.assertEquals(checkOutPage.getSuccessMessage(),"Thank you. Your order has been received.");

    }
   /* @Test
    public void loginAndCheckoutUsingDirectBankTransfe_1() throws InterruptedException, IOException {

        Product product = new Product(1215);
        BillingAddress billingAddress= JacksonUtils.deserializeJSON("myBillingAddress.json",BillingAddress.class);
        User user= new User("Test92140","Test@92140");

        StorePage storePage = new HomePage(getDriver())
                .load()
                .clickStoreMenuLink().search("Blue");

        Thread.sleep(3000);
        Assert.assertTrue(storePage.getTitle().contains("Search results:"));
        storePage.clickAddToCartBtn("Blue Shoes");
        Thread.sleep(10000);

        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(),product.getName());


        CheckOutPage checkOutPage=  cartPage.clickProceedToCheckOutBtn();
        checkOutPage
                .clickHereToLoginLink()
                .login(user)
                .clickLoginBtn();

        checkOutPage
                .setBillingAddress(billingAddress)
                .clickPlaceOrderBtn();

        Thread.sleep(5000);
        Assert.assertEquals(checkOutPage.getSuccessMessage(),"Thank you. Your order has been received.");

    }*/
}
