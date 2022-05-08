package com.hunkikim.fooddelivery.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Getter;

@Getter
public class Order {

    private final UUID orderId;
    private PhoneNumber phoneNumber;
    private String address;
    private String postcode;
    private OrderStatus orderStatus;
    private final List<OrderItem> orderItems;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order(UUID orderId, PhoneNumber phoneNumber, String address, String postcode,
        OrderStatus orderStatus, List<OrderItem> orderItems ,LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.orderId = orderId;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.postcode = postcode;
        this.orderItems = orderItems;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Order(UUID orderId, PhoneNumber phoneNumber, String address, String postcode,
        OrderStatus orderStatus, List<OrderItem> orderItems,
        LocalDateTime createdAt) {
        this.orderId = orderId;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.postcode = postcode;
        this.orderStatus = orderStatus;
        this.orderItems = orderItems;
        this.createdAt = createdAt;
        this.updatedAt = null;
    }
}
