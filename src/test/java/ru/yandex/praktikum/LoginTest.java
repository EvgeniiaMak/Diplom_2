package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.pojo.UserCreateAndLoginResponse;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.Matchers.equalTo;
import static ru.yandex.praktikum.api.UserApi.*;
import static ru.yandex.praktikum.utils.DataPreparer.*;

public class LoginTest {
    private String token;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        UserCreateAndLoginResponse loginResponse = createUser(preparationCreateUserRequest())
                .body().as(UserCreateAndLoginResponse.class);
        token = loginResponse.getAccessToken();
    }

    @Test
    @DisplayName("Test login")
    public void loginTest() {
        Response response = loginUser(preparationUserLoginRequest());
        response.then().assertThat().body("success", equalTo(true))
                .and()
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Test login incorrect email")
    public void loginIncorrectEmailTest() {
        Response response = loginUser(preparationUserLoginRequestIncorrectEmail());
        response.then().assertThat().body("success", equalTo(false))
                .and()
                .statusCode(SC_UNAUTHORIZED);
    }

    @Test
    @DisplayName("Test login incorrect password")
    public void loginIncorrectPasswordTest() {
        Response response = loginUser(preparationUserLoginRequestIncorrectPassword());
        response.then().assertThat().body("success", equalTo(false))
                .and()
                .statusCode(SC_UNAUTHORIZED);
    }

    @After
    public void setDown() {
        deleteUser(token);

    }
}
