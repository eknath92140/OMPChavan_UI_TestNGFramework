package org.selenium.pom.tests;

import io.qameta.allure.*;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataproviders.MyDataProviders;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

@Epic("Test Epic")
@Feature("TEst Feature")
public class AddToCartTest extends BaseTest {

    @Issue("Issue means defect")
    @Link("www.google.com")
    @TmsLink("www.jira.com")
    @Description("This is Description of TEst")
    @Test(description = "Description  User add to cart from Store page")
    public void addToCartFromStorePage( ) throws IOException {

        Product product = new Product(1215);

       CartPage cartPage= new StorePage(getDriver()).load()
               .getProductThumbnail().clickAddToCartBtn(product.getName())
               .clickViewCart();
        Assert.assertEquals(cartPage.getProductName(),product.getName());
    }

    @Test(dataProvider = "getFeaturedProduct", dataProviderClass = MyDataProviders.class)
    @Story("Test Story")
    public void addToCartFeatureProducts(Product product) throws IOException {

        CartPage cartPage = new HomePage(getDriver()).load()
                .getProductThumbnail()
                .clickAddToCartBtn(product.getName())
                .clickViewCart();

        Assert.assertEquals(cartPage.getProductName(),product.getName());
    }
}
