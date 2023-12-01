package com.epam.training.sportsbetting.mvc.login.model;

public class LoginModel {

    private String email;
    private String password;
    private String exceptionMessage;

    public LoginModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginModel() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
