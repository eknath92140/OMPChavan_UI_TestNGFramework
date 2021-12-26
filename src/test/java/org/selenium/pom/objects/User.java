package org.selenium.pom.objects;

public class User {

    private String userName;
    private String passWord;
    private String email;

    public User(){

    }
    public User(String username, String password, String email){
        this.userName=username;
        this.passWord= password;
        this.email = email;
    }
    public User(String username, String password){
        this.userName=username;
        this.passWord= password;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassWord() {
        return passWord;
    }

    public User setPassWord(String passWord) {
        this.passWord = passWord;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }


}
