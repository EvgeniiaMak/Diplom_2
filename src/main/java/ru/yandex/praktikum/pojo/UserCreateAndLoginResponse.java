package ru.yandex.praktikum.pojo;

public class UserCreateAndLoginResponse {


    private UserDto user;
    private boolean success;
    private String accessToken;
    private String refreshToken;

    public UserCreateAndLoginResponse(UserDto user, boolean success, String accessToken, String refreshToken) {
        this.user = user;
        this.success = success;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public UserCreateAndLoginResponse() {
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
