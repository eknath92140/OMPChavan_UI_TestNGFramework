package org.selenium.pom.tests;

import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckOutPage;
import org.selenium.pom.utils.FakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test
    public void loginDuringCheckout() throws IOException, InterruptedException {
        String username = "demouser" + new FakerUtils().generateRandomNumber();

        User user = new User()
                .setUserName(username)
                .setPassWord("passwd")
                .setEmail(username+"@askomdch.com");

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi();
        Product product  = new Product(1215);
        System.out.println("LOGIN COOKIES : "+signUpApi.getCookies());
        cartApi.addToCart(product.getId(),1);

        CheckOutPage checkOutPage= new CheckOutPage(getDriver()).load();

        injectCookiesToBrowser(cartApi.getCookies());

        checkOutPage.load()
                    .clickHereToLoginLink()
                     .login(user);

        Assert.assertTrue(checkOutPage.getProductName().contains(product.getName()));


    }
}
