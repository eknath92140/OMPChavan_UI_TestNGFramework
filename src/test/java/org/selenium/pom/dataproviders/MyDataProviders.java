package org.selenium.pom.dataproviders;

import org.selenium.pom.objects.Product;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyDataProviders {

    @DataProvider(name ="getFeaturedProduct" , parallel = false)
    public Object[] getFeaturedProducts() throws IOException {
        return JacksonUtils.deserializeJSON("products.json", Product[].class);
    }
}
