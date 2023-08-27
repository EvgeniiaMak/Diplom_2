package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.pojo.UserCreateAndLoginResponse;

import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static ru.yandex.praktikum.api.UserApi.*;
import static ru.yandex.praktikum.utils.DataPreparer.*;

public class CreateUserTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @Test
    @DisplayName("Test creating User")
    public void createUserTest() {
        Response response = createUser(preparationCreateUserRequest());
        response.then().assertThat().body("success", equalTo(true))
                .and()
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Test creating duplicated User")
    public void createUserDuplicatedTest() {
        createUser(preparationCreateUserRequest());
        Response response = createUser(preparationCreateUserRequest());
        response.then().assertThat().body("success", equalTo(false))
                .and()
                .body("message", equalTo("User already exists"))
                .and()
                .statusCode(SC_FORBIDDEN);
    }

    @Parameterized.Parameters
    public static Object[] getCreateOrderRequest() {
        return new Object[][]{
                {preparationCreateUserWithoutEmailRequest()},
                {preparationCreateUserWithoutPasswordRequest()},
                {preparationCreateUserWithoutNameRequest()},
        };
    }

    @Test
    @DisplayName("Test creating User Without Email")
    public void createUserWithoutEmailTest() {
        Response response = createUser(preparationCreateUserWithoutEmailRequest());
        response.then().assertThat().body("success", equalTo(false))
                .and()
                .body("message", equalTo("Email, password and name are required fields"))
                .and()
                .statusCode(SC_FORBIDDEN);
    }

    @Test
    @DisplayName("Test creating User Without Password")
    public void createUserWithoutPasswordTest() {
        Response response = createUser(preparationCreateUserWithoutPasswordRequest());
        response.then().assertThat().body("success", equalTo(false))
                .and()
                .body("message", equalTo("Email, password and name are required fields"))
                .and()
                .statusCode(SC_FORBIDDEN);
    }

    @Test
    @DisplayName("Test creating User Without Name")
    public void createUserWithoutNameTest() {
        Response response = createUser(preparationCreateUserWithoutNameRequest());
        response.then().assertThat().body("success", equalTo(false))
                .and()
                .body("message", equalTo("Email, password and name are required fields"))
                .and()
                .statusCode(SC_FORBIDDEN);
    }

    @After
    public void setDown() {
        UserCreateAndLoginResponse loginResponse = loginUser(preparationUserLoginRequest())
                .body().as(UserCreateAndLoginResponse.class);
        if (loginResponse.getAccessToken() != null) {
            deleteUser(loginResponse.getAccessToken());
        }
    }

}
