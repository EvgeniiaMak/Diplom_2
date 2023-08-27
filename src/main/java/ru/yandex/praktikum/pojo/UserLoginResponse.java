package ru.yandex.praktikum.pojo;

public class UserLoginResponse {
    private boolean success;
    private String message;

    public UserLoginResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public UserLoginResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
