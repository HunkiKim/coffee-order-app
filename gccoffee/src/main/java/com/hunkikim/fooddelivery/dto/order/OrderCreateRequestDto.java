package com.hunkikim.fooddelivery.dto.order;

import com.hunkikim.fooddelivery.domain.OrderItem;
import com.hunkikim.fooddelivery.domain.OrderStatus;
import com.hunkikim.fooddelivery.domain.PhoneNumber;
import com.hunkikim.fooddelivery.dto.food.FoodCreateServiceDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Getter;

@Getter
public class OrderCreateRequestDto {

    private final PhoneNumber phoneNumber;
    private final String address;
    private final String postcode;
    private final List<OrderItem> orderItems;

    public OrderCreateRequestDto(String phoneNumber, String address, String postcode,
        List<OrderItem> orderItems) {
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.address = address;
        this.postcode = postcode;
        this.orderItems = orderItems;
    }

    public OrderCreateServiceDto toServiceDto() {
        return new OrderCreateServiceDto(UUID.randomUUID(), this.phoneNumber, this.address,
            this.postcode, OrderStatus.ACCEPT, this.orderItems, LocalDateTime.now(),
            LocalDateTime.now());
    }
}
