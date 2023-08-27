package ru.yandex.praktikum.pojo;

public class UserLogoutRequest {
    private String token;

    public UserLogoutRequest(String token) {
        this.token = token;
    }

    public UserLogoutRequest() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
