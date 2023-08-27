package ru.yandex.praktikum.pojo;

public class IngredientResponse {

    private IngredientDto[] data;

    public IngredientResponse(IngredientDto[] data) {
        this.data = data;
    }

    public IngredientResponse() {
    }

    public IngredientDto[] getData() {
        return data;
    }

    public void setData(IngredientDto[] data) {
        this.data = data;
    }
}
