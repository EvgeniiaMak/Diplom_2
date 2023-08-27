package ru.yandex.praktikum.pojo;

public class OrderCreateResponse {
    private String name;
    private OrderDto order;
    private boolean success;

    public OrderCreateResponse() {
    }

    public OrderCreateResponse(String name, OrderDto order, boolean success) {
        this.name = name;
        this.order = order;
        this.success = success;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrderDto getOrder() {
        return order;
    }

    public void setOrder(OrderDto order) {
        this.order = order;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
