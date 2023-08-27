package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.pojo.UserCreateAndLoginResponse;
import ru.yandex.praktikum.pojo.UserCreateAndUpdateRequest;

import java.util.HashMap;
import java.util.Map;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.Matchers.equalTo;
import static ru.yandex.praktikum.api.UserApi.*;
import static ru.yandex.praktikum.utils.DataPreparer.*;

@RunWith(Parameterized.class)
public class UpdateUserTest {
    private final UserCreateAndUpdateRequest userCreateAndUpdateRequest;
    private String token;

    public UpdateUserTest(UserCreateAndUpdateRequest userCreateAndUpdateRequest) {
        this.userCreateAndUpdateRequest = userCreateAndUpdateRequest;
    }

    @Parameterized.Parameters
    public static Object[] getCreateOrderRequest() {
        return new Object[][]{
                {preparationUpdateNameRequest()},
                {preparationUpdateEmailRequest()},
                {preparationUpdatePasswordRequest()},
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        UserCreateAndLoginResponse loginResponse = createUser(preparationCreateUserRequest())
                .body().as(UserCreateAndLoginResponse.class);
        token = loginResponse.getAccessToken();
    }

    @Test
    @DisplayName("Test update user")
    public void updateUserTest() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("authorization", token);
        Response response = updateUser(userCreateAndUpdateRequest, headers);
        response.then().assertThat().body("success", equalTo(true))
                .and()
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Test update user without token")
    public void updateUserWithoutTokenTest() {
        Map<String, Object> headers = new HashMap<>();
        Response response = updateUser(userCreateAndUpdateRequest, headers);
        response.then().assertThat().body("success", equalTo(false))
                .and()
                .body("message", equalTo("You should be authorised"))
                .and()
                .statusCode(SC_UNAUTHORIZED);
    }

    @After
    public void setDown() {
        deleteUser(token);

    }
}
