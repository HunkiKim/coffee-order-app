package com.hunkikim.fooddelivery.dto.food;

import com.hunkikim.fooddelivery.domain.FoodCategory;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class FoodCreateRequestDto {

    private final String foodName;
    private final FoodCategory foodCategory;
    private final long price;

    public FoodCreateRequestDto(String foodName,
        FoodCategory foodCategory, long price) {
        this.foodName = foodName;
        this.foodCategory = foodCategory;
        this.price = price;
    }

    public FoodCreateServiceDto toServiceDto() {
        return new FoodCreateServiceDto(this.getFoodName(), this.getFoodCategory(), this.getPrice(),
            LocalDateTime.now(), LocalDateTime.now());
    }
}
