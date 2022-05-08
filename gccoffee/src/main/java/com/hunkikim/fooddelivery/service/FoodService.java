package com.hunkikim.fooddelivery.service;

import com.hunkikim.fooddelivery.domain.Food;
import com.hunkikim.fooddelivery.domain.FoodCategory;
import com.hunkikim.fooddelivery.dto.food.FoodCreateServiceDto;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FoodService {

    Food createFood(FoodCreateServiceDto serviceDto);

    List<Food> getFoodByCategory(FoodCategory foodCategory);

    List<Food> getAllFood();

    Optional<Food> findByFoodId(UUID foodId);

    void deleteFoodByFoodId(UUID foodId);

    void deleteAllFood();
}
