package com.hunkikim.fooddelivery.domain;

import java.util.UUID;
import lombok.Getter;

@Getter
public class OrderItem {

    private final UUID foodId;
    private final FoodCategory foodCategory;
    private final long price;
    private final long quantity;

    public OrderItem(UUID foodId, FoodCategory foodCategory, long price, long quantity) {
        this.foodId = foodId;
        this.foodCategory = foodCategory;
        this.price = price;
        this.quantity = quantity;
    }

    public UUID getFoodId() {
        return foodId;
    }

    public FoodCategory getFoodCategory() {
        return foodCategory;
    }

    public long getPrice() {
        return price;
    }

    public long getQuantity() {
        return quantity;
    }
}
