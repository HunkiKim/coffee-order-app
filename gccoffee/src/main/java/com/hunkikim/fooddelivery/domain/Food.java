package com.hunkikim.fooddelivery.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Food {

    private final UUID foodId;
    private String foodName;
    private FoodCategory foodCategory;
    private long price;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Food(UUID foodId, String foodName,
        FoodCategory foodCategory, long price, LocalDateTime createdAt,
        LocalDateTime updatedAt) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodCategory = foodCategory;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Food(UUID foodId, String foodName, FoodCategory foodCategory, long price,
        LocalDateTime createdAt) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodCategory = foodCategory;
        this.price = price;
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Food)) {
            return false;
        }
        Food food = (Food) o;
        return getFoodId().equals(food.getFoodId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFoodId());
    }
}
