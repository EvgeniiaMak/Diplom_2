package ru.yandex.praktikum.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.praktikum.pojo.UserCreateAndUpdateRequest;
import ru.yandex.praktikum.pojo.UserLoginRequest;
import ru.yandex.praktikum.pojo.UserLogoutRequest;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserApi {
    private final static String REGISTER_URL = "/api/auth/register";
    private final static String LOGIN_URL = "/api/auth/login";
    private final static String LOGOUT_URL = "/api/auth/logout";
    private final static String USER_URL = "/api/auth/user";


    @Step("Send POST request to /api/auth/register")
    public static Response createUser(UserCreateAndUpdateRequest request) {
        return given()
                .header("Content-type", "application/json")
                .body(request)
                .post(REGISTER_URL);
    }

    @Step("Send POST request to /api/auth/login")
    public static Response loginUser(UserLoginRequest request) {
        return given()
                .header("Content-type", "application/json")
                .body(request)
                .post(LOGIN_URL);
    }

    @Step("Send POST request to /api/auth/logout")
    public static Response logoutUser(UserLogoutRequest request, String token) {
        return given()
                .header("Content-type", "application/json")
                .header("authorization", token)
                .body(request)
                .post(LOGOUT_URL);
    }

    @Step("Send POST request to /api/auth/user")
    public static Response updateUser(UserCreateAndUpdateRequest request, Map<String, Object> headers) {
        return given()
                .header("Content-type", "application/json")
                .headers(headers)
                .body(request)
                .patch(USER_URL);
    }

    @Step("Send GET request to /api/auth/user")
    public static Response getUser(String token) {
        return given()
                .header("Content-type", "application/json")
                .header("authorization", token)
                .get(USER_URL);
    }

    @Step("Send DELETE request to /api/auth/user")
    public static Response deleteUser(String token) {
        return given()
                .header("Content-type", "application/json")
                .header("authorization", token)
                .delete(USER_URL);
    }

}
