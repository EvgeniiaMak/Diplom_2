package ru.yandex.praktikum.pojo;

public class IngredientDto {

    private String _id;


    public IngredientDto() {
    }

    public IngredientDto(String _id) {
        this._id = _id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
