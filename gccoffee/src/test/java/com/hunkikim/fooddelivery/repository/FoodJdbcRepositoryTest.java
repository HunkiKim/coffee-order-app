package com.hunkikim.fooddelivery.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import com.hunkikim.fooddelivery.EmbbedDockerDatabase;
import com.hunkikim.fooddelivery.domain.Food;
import com.hunkikim.fooddelivery.domain.FoodCategory;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.dao.DuplicateKeyException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FoodJdbcRepositoryTest {

    private final static FoodRepository foodRepository = new FoodJdbcRepository(
        EmbbedDockerDatabase.getDataSource());

    private static final UUID uuid = UUID.randomUUID();
    private static final Food test_food = new Food(uuid, "ramen", FoodCategory.JAPANESE_STYLE,
        10000L, LocalDateTime.now(), LocalDateTime.now());

    @AfterEach
    void afterEach() {
        foodRepository.deleteAll();
    }

    @Test
    void insertFood_정상_테스트() {
        //when
        foodRepository.insertFood(test_food);
        //then
        assertThat(foodRepository.findByFoodId(uuid)).isNotEmpty();
    }

    @Test
    void insertFood_비정상_테스트() {
        //given
        foodRepository.insertFood(test_food);
        Food test_food2 = new Food(uuid, "짜장면", FoodCategory.CHINESE_STYLE, 1000L,
            LocalDateTime.now(), LocalDateTime.now());
        //then
        assertThatCode(() -> {
            foodRepository.insertFood(test_food2);
        }).isInstanceOf(DuplicateKeyException.class);
    }

    @Test
    void findByFoodId_정상_테스트() {
        //given
        foodRepository.insertFood(test_food);
        //when
        var food = foodRepository.findByFoodId(uuid);
        //then
        assertThat(food).isNotEmpty().get().isEqualTo(test_food);
    }

    @Test
    void deleteByFoodId_정상_테스트() {
        //given
        foodRepository.insertFood(test_food);
        //when
        foodRepository.deleteByFoodId(uuid);
        //then
        assertThat(foodRepository.findByFoodId(uuid)).isEmpty();
    }

    @Test
    void deleteAll_정상_테스트() {
        //given
        foodRepository.insertFood(test_food);
        Food test_food2 = new Food(UUID.randomUUID(), "짜장면", FoodCategory.CHINESE_STYLE, 1000L,
            LocalDateTime.now(), LocalDateTime.now());
        foodRepository.insertFood(test_food2);
        //when
        foodRepository.deleteAll();
        //then
        assertThat(foodRepository.findAllFood()).isEmpty();
    }
}