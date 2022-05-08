package com.hunkikim.fooddelivery.controller;

import com.hunkikim.fooddelivery.domain.Food;
import com.hunkikim.fooddelivery.dto.food.FoodCreateRequestDto;
import com.hunkikim.fooddelivery.dto.food.FoodCreateServiceDto;
import com.hunkikim.fooddelivery.service.DefaultFoodService;
import com.hunkikim.fooddelivery.service.FoodService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodRestController {
    private final FoodService foodService;

    public FoodRestController(DefaultFoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/api/v1/food")
    public List<Food> getFoodList() {
        return foodService.getAllFood();
    }

    @PostMapping("api/v1/food/new")
    public Food addFood(@RequestBody FoodCreateRequestDto createRequestDto) {
        return foodService.createFood(createRequestDto.toServiceDto());
    }
}
