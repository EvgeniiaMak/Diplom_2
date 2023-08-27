package ru.yandex.praktikum.pojo;

public class OrderCreateRequest {
    private String[] ingredients;

    public OrderCreateRequest(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public OrderCreateRequest() {
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }
}
