package org.selenium.pom.api.actions;

import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.selenium.pom.objects.User;
import org.selenium.pom.utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class SignUpApi {


    private Cookies cookies;

    public Cookies getCookies(){
        return cookies;
    }

    private String fetchRegisterNonceValue_UsingGrrovy(){
        Response response = getAccount();
      return response.htmlPath().getString("**.findAll { it.@name == 'woocommerce-register-nonce' }.@value");

    }


    private String fetchRegisterNonceValue_UsingJSoup(){
        Response response = getAccount();
        Document doc =  Jsoup.parse(response.body().prettyPrint());
        Element element = doc.selectFirst("#woocommerce-register-nonce");
        return element.attr("value");

    }

    public Response getAccount(){
        Cookies cookies= new Cookies(); // create empty coockies
        Response response = given().
                                    baseUri(ConfigLoader.getInstance().getPropertyValue("baseUrl"))
                                    .cookies(cookies)
                                    .log().all().
                            when()
                                    .get("/account").
                            then()
                                    .log().all().
                                    extract().
                                    response();
        if(response.getStatusCode()!=200){
                throw new RuntimeException("Failed to fetch Account . HTTP status code: "+ response.getStatusCode());
        }
        return response;
    }

    public Response register(User user){
        Cookies cookies= new Cookies(); // create empty coockies
        Header header = new Header("content-type","application/x-www-form-urlencoded");
        Headers headers = new Headers(header);

        HashMap<String, String> formsparam = new HashMap<>();
        formsparam.put("username",user.getUserName());
        formsparam.put("email",user.getEmail());
        formsparam.put("password",user.getPassWord());
        formsparam.put("woocommerce-register-nonce",fetchRegisterNonceValue_UsingGrrovy());
        formsparam.put("_wp_http_referer","/account/");
        formsparam.put("register","Register");

        Response response = given().
                                    baseUri(ConfigLoader.getInstance().getPropertyValue("baseUrl"))
                                    .headers(headers)
                                    .formParams(formsparam)
                                    .cookies(cookies)
                                    .log().all().
                             when()
                                    .post("/account").
                            then()
                                    .log().all().
                                    extract().
                                     response();
        if(response.getStatusCode()!=302){
            throw new RuntimeException("Failed to register  Account . HTTP status code: "+ response.getStatusCode());
        }

        // As we need cookies to set for above as level var so hat further calls can be resolved ,
        // hence we fetch cookies from this response and set to that instance var
        this.cookies = response.getDetailedCookies();
        return response;
    }

}
