package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.pojo.IngredientDto;
import ru.yandex.praktikum.pojo.IngredientResponse;
import ru.yandex.praktikum.pojo.UserCreateAndLoginResponse;

import java.util.HashMap;
import java.util.Map;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static ru.yandex.praktikum.api.OrderApi.createOrders;
import static ru.yandex.praktikum.api.OrderApi.getIngredients;
import static ru.yandex.praktikum.api.UserApi.createUser;
import static ru.yandex.praktikum.api.UserApi.deleteUser;
import static ru.yandex.praktikum.utils.DataPreparer.*;

public class CreateOrderTest {
    private String token;
    private IngredientDto[] ingredients;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        UserCreateAndLoginResponse loginResponse = createUser(preparationCreateUserRequest())
                .body().as(UserCreateAndLoginResponse.class);
        token = loginResponse.getAccessToken();
        ingredients = getIngredients().body().as(IngredientResponse.class).getData();
    }

    @Test
    @DisplayName("Test create order")
    public void createOrderTest() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("authorization", token);
        Response response = createOrders(preparationOrderCreateRequest(ingredients), headers);
        response.then().assertThat().body("success", equalTo(true))
                .and()
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Test create order without authorization")
    public void createOrderWithoutAuthorizationTest() {
        Map<String, Object> headers = new HashMap<>();
        Response response = createOrders(preparationOrderCreateRequest(ingredients), headers);
        response.then().assertThat().body("success", equalTo(true))
                .and()
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Test create order without ingredients")
    public void createWithoutIngredientsTest() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("authorization", token);
        Response response = createOrders(preparationOrderCreateRequestWithoutIngredients(), headers);
        response.then().assertThat().body("success", equalTo(false))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Test create order with incorrect ingredient hash")
    public void createOrderIncorrectIngredientHashTest() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("authorization", token);
        Response response = createOrders(preparationOrderCreateRequestIncorrectHash(), headers);
        response.then().assertThat()
                .statusCode(SC_INTERNAL_SERVER_ERROR);
    }

    @After
    public void setDown() {
        deleteUser(token);
    }
}
