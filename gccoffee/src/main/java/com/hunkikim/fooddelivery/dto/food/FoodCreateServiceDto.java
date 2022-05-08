package com.hunkikim.fooddelivery.dto.food;

import com.hunkikim.fooddelivery.domain.FoodCategory;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FoodCreateServiceDto {
    private final String foodName;
    private final FoodCategory foodCategory;
    private final long price;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
