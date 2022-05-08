package com.hunkikim.fooddelivery.repository;

import com.hunkikim.fooddelivery.domain.Food;
import com.hunkikim.fooddelivery.domain.FoodCategory;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FoodRepository {

    Food insertFood(Food food);

    Optional<Food> findByFoodId(UUID foodId);

    List<Food> findByFoodCategory(FoodCategory foodCategory);

    List<Food> findAllFood();

    void deleteByFoodId(UUID foodId);

    void deleteAll();
}
