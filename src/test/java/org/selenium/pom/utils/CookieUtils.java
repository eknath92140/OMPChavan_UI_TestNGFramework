package org.selenium.pom.utils;

import io.restassured.http.Cookies;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.List;

public class CookieUtils {

    public List<Cookie> convertRsetAssuredCookiestoSeleniumCookies(Cookies cookies){

        List<io.restassured.http.Cookie> restAssuredCookies = new ArrayList<>();
        List<Cookie> seleniumCookies = new ArrayList<>();

        restAssuredCookies= cookies.asList();

        for(io.restassured.http.Cookie coockie : restAssuredCookies){
            seleniumCookies.add(new Cookie(
                    coockie.getName(),coockie.getValue(),coockie.getDomain(),
                    coockie.getPath(),coockie.getExpiryDate(),coockie.isSecured(),
                    coockie.isHttpOnly(),coockie.getSameSite()
            ));
        }

        return seleniumCookies;
    }
}
