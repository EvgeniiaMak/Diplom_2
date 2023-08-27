package ru.yandex.praktikum.pojo;

public class OrderDto {
    private int number;
    private String[] ingredients;
    private String _id;
    private String status;
    private String createdAt;
    private String updatedAt;

    public OrderDto() {
    }

    public OrderDto(int number, String[] ingredients, String _id, String status, String createdAt, String updatedAt) {
        this.number = number;
        this.ingredients = ingredients;
        this._id = _id;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
