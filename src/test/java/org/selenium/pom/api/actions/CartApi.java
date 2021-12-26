package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.selenium.pom.objects.User;
import org.selenium.pom.utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class CartApi {
    private Cookies cookies;

       public CartApi(){
            // this ctor will called when want to call for guest user
    }
    public CartApi(Cookies cookies){
        this.cookies = cookies;
        // this ctor will called when want to call for logged-in user
    }

    public Cookies getCookies(){
           return cookies;
    }

    public Response addToCart(int productID , int quantity){


        Header header = new Header("content-type","application/x-www-form-urlencoded");
        Headers headers = new Headers(header);

        HashMap<String, Object> formsparam = new HashMap<>();
        formsparam.put("product_sku","");
        formsparam.put("product_id",productID);
        formsparam.put("quantity",quantity);

        if(cookies==null){
            cookies= new Cookies(); // create empty cookies
        }

        Response response = given().
                                    baseUri(ConfigLoader.getInstance().getPropertyValue("baseUrl"))
                                    .headers(headers)
                                    .formParams(formsparam)
                                    .cookies(cookies)
                                    .log().all().
                            when()
                                    .post("/?wc-ajax=add_to_cart").
                            then()
                                    .log().all().
                                     extract().
                                      response();
        if(response.getStatusCode()!=200){
            throw new RuntimeException("Failed to add product "+ productID +" to Cart . HTTP status code: "+ response.getStatusCode());
        }

        this.cookies = response.getDetailedCookies();
        return response;
    }

}
