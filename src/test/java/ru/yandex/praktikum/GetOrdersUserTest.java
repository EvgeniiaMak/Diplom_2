package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.pojo.IngredientDto;
import ru.yandex.praktikum.pojo.IngredientResponse;
import ru.yandex.praktikum.pojo.OrderResponse;
import ru.yandex.praktikum.pojo.UserCreateAndLoginResponse;

import java.util.HashMap;
import java.util.Map;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static ru.yandex.praktikum.api.OrderApi.*;
import static ru.yandex.praktikum.api.UserApi.createUser;
import static ru.yandex.praktikum.api.UserApi.deleteUser;
import static ru.yandex.praktikum.utils.DataPreparer.preparationCreateUserRequest;
import static ru.yandex.praktikum.utils.DataPreparer.preparationOrderCreateRequest;

public class GetOrdersUserTest {

    private String token;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        UserCreateAndLoginResponse loginResponse = createUser(preparationCreateUserRequest())
                .body().as(UserCreateAndLoginResponse.class);
        token = loginResponse.getAccessToken();
        IngredientDto[] ingredients = getIngredients().body().as(IngredientResponse.class).getData();
        Map<String, Object> headers = new HashMap<>();
        headers.put("authorization", token);
        createOrders(preparationOrderCreateRequest(ingredients), headers);
        createOrders(preparationOrderCreateRequest(ingredients), headers);
        createOrders(preparationOrderCreateRequest(ingredients), headers);
        createOrders(preparationOrderCreateRequest(ingredients), headers);
    }

    @Test
    @DisplayName("Test get user orders with authorization")
    public void getUserOrdersWithAuthorization() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("authorization", token);
        Response response = getOrders(headers);
        response.then().assertThat()
                .statusCode(SC_OK);
        OrderResponse orderResponse = response.body().as(OrderResponse.class);
        MatcherAssert.assertThat(orderResponse.getOrders().length, greaterThan(0));
    }

    @Test
    @DisplayName("Test get user orders without authorization")
    public void getUserOrdersWithoutAuthorization() {
        Map<String, Object> headers = new HashMap<>();
        Response response = getOrders(headers);
        response.then().assertThat().body("success", equalTo(false))
                .and()
                .statusCode(SC_UNAUTHORIZED);
    }

    @After
    public void setDown() {
        deleteUser(token);

    }
}
