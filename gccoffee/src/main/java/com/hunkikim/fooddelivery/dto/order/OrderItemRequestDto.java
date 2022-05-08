package com.hunkikim.fooddelivery.dto.order;

import com.hunkikim.fooddelivery.domain.FoodCategory;
import lombok.Getter;

@Getter
public class OrderItemRequestDto {

    private final FoodCategory foodCategory;
    private final long price;
    private final long quantity;

    public OrderItemRequestDto(FoodCategory foodCategory, long price, long quantity) {
        this.foodCategory = foodCategory;
        this.price = price;
        this.quantity = quantity;
    }
}
