package com.hunkikim.fooddelivery.dto.order;

import com.hunkikim.fooddelivery.domain.OrderItem;
import com.hunkikim.fooddelivery.domain.OrderStatus;
import com.hunkikim.fooddelivery.domain.PhoneNumber;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Getter;

@Getter
public class OrderCreateServiceDto {
    private final UUID orderId;
    private PhoneNumber phoneNumber;
    private String address;
    private String postcode;
    private OrderStatus orderStatus;
    private final List<OrderItem> orderItems;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public OrderCreateServiceDto(UUID orderId,
        PhoneNumber phoneNumber, String address, String postcode,
        OrderStatus orderStatus,
        List<OrderItem> orderItems, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.orderId = orderId;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.postcode = postcode;
        this.orderStatus = orderStatus;
        this.orderItems = orderItems;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
