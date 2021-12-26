package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

// below class will focus on Navigation functionality test cases for any features.
public class NavigationTest extends BaseTest {

    @Test
    public void NavigateFromHomeToStoreusingMainMenu(){

        StorePage storePage = new HomePage(getDriver())
                .load()
                .getMyHeader()
                .clickStoreMenuLink();
        Assert.assertTrue(storePage.getTitle().contains("Store"));
    }
}
