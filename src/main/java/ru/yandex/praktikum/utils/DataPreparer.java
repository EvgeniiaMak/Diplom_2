package ru.yandex.praktikum.utils;

import ru.yandex.praktikum.pojo.IngredientDto;
import ru.yandex.praktikum.pojo.OrderCreateRequest;
import ru.yandex.praktikum.pojo.UserCreateAndUpdateRequest;
import ru.yandex.praktikum.pojo.UserLoginRequest;

import static ru.yandex.praktikum.utils.TestConstants.*;


public final class DataPreparer {

    private DataPreparer() {
    }

    public static UserCreateAndUpdateRequest preparationCreateUserRequest() {
        return new UserCreateAndUpdateRequest(TEST_USER_EMAIL, TEST_USER_PASSWORD, TEST_USER_NAME);
    }

    public static UserCreateAndUpdateRequest preparationCreateUserWithoutEmailRequest() {
        return new UserCreateAndUpdateRequest(null, TEST_USER_PASSWORD, TEST_USER_NAME);
    }

    public static UserCreateAndUpdateRequest preparationCreateUserWithoutPasswordRequest() {
        return new UserCreateAndUpdateRequest(TEST_USER_EMAIL, null, TEST_USER_NAME);
    }

    public static UserCreateAndUpdateRequest preparationCreateUserWithoutNameRequest() {
        return new UserCreateAndUpdateRequest(TEST_USER_EMAIL, TEST_USER_PASSWORD, null);
    }


    public static UserLoginRequest preparationUserLoginRequest() {
        return new UserLoginRequest(TEST_USER_EMAIL, TEST_USER_PASSWORD);
    }

    public static UserLoginRequest preparationUserLoginRequestIncorrectEmail() {
        return new UserLoginRequest(TEST_USER_INCORRECT_EMAIL, TEST_USER_PASSWORD);
    }

    public static UserLoginRequest preparationUserLoginRequestIncorrectPassword() {
        return new UserLoginRequest(TEST_USER_EMAIL, TEST_USER_INCORRECT_PASSWORD);
    }


    public static UserCreateAndUpdateRequest preparationUpdateNameRequest() {
        return new UserCreateAndUpdateRequest(null, null, TEST_UPDATE_USER_NAME);
    }

    public static UserCreateAndUpdateRequest preparationUpdateEmailRequest() {
        return new UserCreateAndUpdateRequest(null, TEST_UPDATE_USER_PASSWORD, null);
    }

    public static UserCreateAndUpdateRequest preparationUpdatePasswordRequest() {
        return new UserCreateAndUpdateRequest(TEST_UPDATE_USER_EMAIL, null, null);
    }

    public static OrderCreateRequest preparationOrderCreateRequest(IngredientDto[] ingredients) {
        String[] ingredientsForOrder = new String[]{ingredients[0].get_id(), ingredients[1].get_id()};
        return new OrderCreateRequest(ingredientsForOrder);
    }

    public static OrderCreateRequest preparationOrderCreateRequestWithoutIngredients() {
        String[] ingredientsForOrder = new String[]{};
        return new OrderCreateRequest(ingredientsForOrder);
    }

    public static OrderCreateRequest preparationOrderCreateRequestIncorrectHash() {
        String[] ingredientsForOrder = new String[]{INCORRECT_INGREDIENT_HASH};
        return new OrderCreateRequest(ingredientsForOrder);
    }

}
