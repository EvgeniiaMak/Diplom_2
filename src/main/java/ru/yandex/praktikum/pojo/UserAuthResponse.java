package ru.yandex.praktikum.pojo;

public class UserAuthResponse {
    private UserDto user;
    private boolean success;

    public UserAuthResponse() {
    }

    public UserAuthResponse(UserDto user, boolean success) {
        this.user = user;
        this.success = success;
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
}
