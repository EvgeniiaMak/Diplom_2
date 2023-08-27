package ru.yandex.praktikum.pojo;

public class OrderResponse {
    private boolean success;
    private OrderDto[] orders;
    private int total;
    private int totalToday;

    public OrderResponse() {
    }

    public OrderResponse(boolean success, OrderDto[] orders, int total, int totalToday) {
        this.success = success;
        this.orders = orders;
        this.total = total;
        this.totalToday = totalToday;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public OrderDto[] getOrders() {
        return orders;
    }

    public void setOrders(OrderDto[] orders) {
        this.orders = orders;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalToday() {
        return totalToday;
    }

    public void setTotalToday(int totalToday) {
        this.totalToday = totalToday;
    }
}
