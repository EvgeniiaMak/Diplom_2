package ru.yandex.praktikum.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.praktikum.pojo.OrderCreateRequest;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class OrderApi {

    private final static String INGREDIENTS_URL = "/api/ingredients";
    private final static String ALL_ORDERS_URL = "/api/orders/all";
    private final static String ORDERS_URL = "/api/orders";

    @Step("Send GET request to /api/ingredients")
    public static Response getIngredients() {
        return given()
                .header("Content-type", "application/json")
                .get(INGREDIENTS_URL);
    }

    @Step("Send POST request to /api/orders")
    public static Response createOrders(OrderCreateRequest request, Map<String, Object> headers) {
        return given()
                .header("Content-type", "application/json")
                .headers(headers)
                .body(request)
                .post(ORDERS_URL);
    }

    @Step("Send GET request to /api/orders/all")
    public static Response getAllOrders() {
        return given()
                .header("Content-type", "application/json")
                .get(ALL_ORDERS_URL);
    }

    @Step("Send GET request to /api/orders")
    public static Response getOrders(Map<String, Object> headers) {
        return given()
                .header("Content-type", "application/json")
                .headers(headers)
                .get(ORDERS_URL);
    }

}
