package com.hunkikim.fooddelivery.controller;

import com.hunkikim.fooddelivery.domain.Order;
import com.hunkikim.fooddelivery.dto.order.OrderCreateRequestDto;
import com.hunkikim.fooddelivery.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {
    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/api/v1/order/new")
    public Order createOrder(@RequestBody OrderCreateRequestDto orderCreateRequestDto) {
        return orderService.createOrder(orderCreateRequestDto.toServiceDto());
    }
}
