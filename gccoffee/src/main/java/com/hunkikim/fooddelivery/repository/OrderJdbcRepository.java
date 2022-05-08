package com.hunkikim.fooddelivery.repository;

import static com.hunkikim.fooddelivery.JdbcUtils.toLocalDateTime;
import static com.hunkikim.fooddelivery.JdbcUtils.toUUID;

import com.hunkikim.fooddelivery.domain.Food;
import com.hunkikim.fooddelivery.domain.FoodCategory;
import com.hunkikim.fooddelivery.domain.Order;
import com.hunkikim.fooddelivery.domain.OrderItem;
import com.hunkikim.fooddelivery.domain.OrderStatus;
import com.hunkikim.fooddelivery.domain.PhoneNumber;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class OrderJdbcRepository implements OrderRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public OrderJdbcRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    @Transactional
    public Order insert(Order order) {
        jdbcTemplate.update(
            "INSERT INTO orders(order_id, phone_number, address, postcode, order_status, created_at, updated_at) VALUES (UUID_TO_BIN(:orderId), :phoneNumber, :address, :postcode, :orderStatus, :createdAt,:updatedAt)",
            toOrderParamMap(order));
        order.getOrderItems().forEach(item -> jdbcTemplate.update(
            "INSERT INTO order_item(order_id, food_id, category, price, quantity, created_at, updated_at)"
                + "VALUES (UUID_TO_BIN(:orderId), UUID_TO_BIN(:foodId), :foodCategory, :price, :quantity, :createdAt, :updatedAt)",
            toOrderItemParamMap(order.getOrderId(), order.getCreatedAt(), order.getUpdatedAt(),
                item)));
        return order;
    }

    private Map<String, Object> toOrderParamMap(Order order) {
        var paramMap = new HashMap<String, Object>();
        paramMap.put("orderId", order.getOrderId().toString().getBytes());
        paramMap.put("phoneNumber", order.getPhoneNumber().getPhoneNumber());
        paramMap.put("address", order.getAddress());
        paramMap.put("postcode", order.getPostcode());
        paramMap.put("orderStatus", order.getOrderStatus().toString());
        paramMap.put("createdAt", order.getCreatedAt());
        paramMap.put("updatedAt", order.getUpdatedAt());
        return paramMap;
    }

    private Map<String, Object> toOrderItemParamMap(UUID orderId, LocalDateTime createdAt,
        LocalDateTime updatedAt, OrderItem item) {
        var paramMap = new HashMap<String, Object>();
        paramMap.put("orderId", orderId.toString().getBytes());
        paramMap.put("foodId", item.getFoodId().toString().getBytes());
        paramMap.put("foodCategory", item.getFoodCategory().toString());
        paramMap.put("price", item.getPrice());
        paramMap.put("quantity", item.getQuantity());
        paramMap.put("createdAt", createdAt);
        paramMap.put("updatedAt", updatedAt);
        return paramMap;
    }
}
