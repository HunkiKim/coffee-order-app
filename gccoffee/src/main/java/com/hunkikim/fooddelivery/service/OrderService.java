package com.hunkikim.fooddelivery.service;

import com.hunkikim.fooddelivery.domain.Order;
import com.hunkikim.fooddelivery.domain.OrderItem;
import com.hunkikim.fooddelivery.domain.PhoneNumber;
import com.hunkikim.fooddelivery.dto.order.OrderCreateServiceDto;
import java.util.List;

public interface OrderService {
    Order createOrder(OrderCreateServiceDto orderCreateServiceDto);
}
