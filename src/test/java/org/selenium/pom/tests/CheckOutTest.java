package org.selenium.pom.tests;

import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckOutPage;
import org.selenium.pom.utils.FakerUtils;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckOutTest extends BaseTest {

    @Test
    public void guestLoginUsingDirectBankTransafer() throws IOException, InterruptedException {

        BillingAddress billingAddress= JacksonUtils.deserializeJSON("myBillingAddress.json",BillingAddress.class);

        CheckOutPage checkOutPage = new CheckOutPage(getDriver()).load();

        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215,1);

        injectCookiesToBrowser(cartApi.getCookies());
        checkOutPage.load()
                .setBillingAddress(billingAddress)
                .clickPlaceOrderBtn();

        Assert.assertEquals(checkOutPage.getSuccessMessage(),"Thank you. Your order has been received.");
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransafer() throws IOException, InterruptedException {

        BillingAddress billingAddress= JacksonUtils.deserializeJSON("myBillingAddress.json",BillingAddress.class);
        String username = "demouser" + new FakerUtils().generateRandomNumber();

        User user = new User()
                .setUserName(username)
                .setPassWord("passwd")
                .setEmail(username+"@askomdch.com");

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        Product product  = new Product(1215);
        cartApi.addToCart(product.getId(),1);

        CheckOutPage checkOutPage= new CheckOutPage(getDriver()).load();
        injectCookiesToBrowser(signUpApi.getCookies());
        checkOutPage.load();
        Thread.sleep(5000);
        checkOutPage.load()
                    .setBillingAddress(billingAddress)
                    .selectCashOnDeliveryRadioBtn()
                .clickPlaceOrderBtn();

        Assert.assertEquals(checkOutPage.getSuccessMessage(),"Thank you. Your order has been received.");

        // note : in this Tas we are doing Login & add to cart using API to maintenain app state, we dont see Login
        // Link on Checkout which we launch as 1st UI step in this TC
    }
}
