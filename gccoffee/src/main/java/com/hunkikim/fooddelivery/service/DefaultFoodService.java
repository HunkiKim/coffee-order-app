package com.hunkikim.fooddelivery.service;

import com.hunkikim.fooddelivery.domain.Food;
import com.hunkikim.fooddelivery.domain.FoodCategory;
import com.hunkikim.fooddelivery.dto.food.FoodCreateServiceDto;
import com.hunkikim.fooddelivery.repository.FoodRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class DefaultFoodService implements FoodService {

    private final FoodRepository foodRepository;

    public DefaultFoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public Food createFood(FoodCreateServiceDto serviceDto) {
        return foodRepository.insertFood(
            new Food(UUID.randomUUID(), serviceDto.getFoodName(), serviceDto.getFoodCategory(),
                serviceDto.getPrice(), serviceDto.getCreatedAt(), serviceDto.getUpdatedAt()));
    }

    @Override
    public List<Food> getFoodByCategory(FoodCategory foodCategory) {
        return foodRepository.findByFoodCategory(foodCategory);
    }

    @Override
    public List<Food> getAllFood() {
        return foodRepository.findAllFood();
    }

    @Override
    public Optional<Food> findByFoodId(UUID foodId) {
        return foodRepository.findByFoodId(foodId);
    }

    @Override
    public void deleteFoodByFoodId(UUID foodId) {
        foodRepository.deleteByFoodId(foodId);
    }

    @Override
    public void deleteAllFood() {
        foodRepository.deleteAll();
    }
}
