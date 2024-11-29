package com.fragrancestore.model;

import java.util.List;

public class Order {
    private int orderId; 
    private int userId;  
    private int orderQuantity; 
    private String orderDate;  
    private double totalPrice; 
    private List<Product> products; 

    public Order() {
    }

    public Order(int orderId, int userId, int orderQuantity, String orderDate, double totalPrice) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderQuantity = orderQuantity;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order [orderId=" + orderId + ", userId=" + userId + ", orderQuantity=" + orderQuantity +
                ", orderDate=" + orderDate + ", totalPrice=" + totalPrice + ", products=" + products + "]";
    }
}
