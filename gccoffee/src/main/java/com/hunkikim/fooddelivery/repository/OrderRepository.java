package com.hunkikim.fooddelivery.repository;

import com.hunkikim.fooddelivery.domain.Order;
import java.util.List;
import java.util.UUID;

public interface OrderRepository {

    Order insert(Order order);
}
