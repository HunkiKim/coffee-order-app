package com.hunkikim.fooddelivery.repository;

import com.hunkikim.fooddelivery.domain.Food;
import com.hunkikim.fooddelivery.domain.FoodCategory;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import static com.hunkikim.fooddelivery.JdbcUtils.*;

@Repository
public class FoodJdbcRepository implements FoodRepository {

    private static final Logger logger = LoggerFactory.getLogger(FoodJdbcRepository.class);
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public FoodJdbcRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private Map<String, Object> toParaMap(Food food) {
        HashMap<String, Object> paraMap = new HashMap<>();
        paraMap.put("foodId", food.getFoodId().toString().getBytes());
        paraMap.put("foodName", food.getFoodName());
        paraMap.put("category", food.getFoodCategory().toString());
        paraMap.put("price", food.getPrice());
        paraMap.put("createdAt", food.getCreatedAt());
        paraMap.put("updatedAt", food.getUpdatedAt());
        return paraMap;
    }

    private static final RowMapper<Food> foodRowMapper = (resultSet, i) -> {
        var foodId = toUUID(resultSet.getBytes("food_id"));
        var foodName = resultSet.getString("food_name");
        var foodCategory = FoodCategory.valueOf(resultSet.getString("category"));
        var price = resultSet.getLong("price");
        var createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
        var updatedAt = toLocalDateTime(resultSet.getTimestamp("updated_at"));
        return new Food(foodId, foodName, foodCategory, price, createdAt, updatedAt);
    };

    @Override
    public Food insertFood(Food food) {
        try {
            var update = jdbcTemplate.update(
                "insert into food(food_id, food_name, category, price, created_at, updated_at)"
                    + " VALUES(UUID_TO_BIN(:foodId), :foodName, :category, :price, :createdAt, :updatedAt)",
                toParaMap(food));
            if (update != 1) {
                throw new IllegalArgumentException("Nothing was inserted");
            }
        } catch (IllegalArgumentException e) {
            logger.error("Nothing was inserted", e);
        } catch (DuplicateKeyException e) {
            logger.error("Key is duplicated", e);
            throw new DuplicateKeyException("Key is duplicated");
        }
        return food;
    }
    
    @Override
    public Optional<Food> findByFoodId(UUID foodId) {
        try {
            var food = Optional.ofNullable(
                jdbcTemplate.queryForObject(
                    "SELECT * FROM food WHERE food_id = UUID_TO_BIN(:foodId)",
                    Collections.singletonMap("foodId", foodId.toString().getBytes()),
                    foodRowMapper));
            if (food.isEmpty()) {
                throw new EmptyResultDataAccessException(1);
            }
            return food;
        } catch (EmptyResultDataAccessException e) {
            logger.error("Food table is empty");
            return Optional.empty();
        }
    }

    @Override
    public List<Food> findByFoodCategory(FoodCategory foodCategory) {
        return jdbcTemplate.query("SELECT * FROM food WHERE category = :category", Collections.singletonMap("category", foodCategory.toString()), foodRowMapper);
    }

    @Override
    public List<Food> findAllFood() {
        return jdbcTemplate.query("SELECT * FROM food", Collections.emptyMap(),foodRowMapper);
    }

    @Override
    public void deleteByFoodId(UUID foodId) {
        try {
            var food = jdbcTemplate.update("DELETE FROM food WHERE food_id = UUID_TO_BIN(:foodId)",
                Collections.singletonMap("foodId", foodId.toString().getBytes()));
            if (food != 1) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            logger.error("Nothing was deleted.");
        }
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM food", Collections.emptyMap());
    }
}
