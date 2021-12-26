package org.selenium.pom.api.actions;

import org.selenium.pom.objects.User;
import org.selenium.pom.utils.FakerUtils;

public class DummyAPiClass {

    public static void main(String[] args) {
        CartApi cartApi;
        // below Scenario flow is for Guest User add to cart  #########################
       /* CartApi cartApi = new CartApi();
        cartApi.addToCart("1205","1");
        System.out.println("ADD_CART COOKIES : "+cartApi.getCookies());*/

        // below Scenario flow is for Loggedin User add to cart  ###################
        String username = "demouser" + new FakerUtils().generateRandomNumber();

        User user = new User()
                .setUserName(username)
                .setPassWord("passwd")
                .setEmail(username+"@askomdch.com");
        SignUpApi signUpApi = new SignUpApi();

        signUpApi.register(user);
        System.out.println("LOGIN COOKIES : "+signUpApi.getCookies());

        cartApi = new CartApi(signUpApi.getCookies());
        cartApi.addToCart(1205,1);
        System.out.println("ADD_CART COOKIES : "+cartApi.getCookies());
    }
}
