package com.hunkikim.fooddelivery.service;

import com.hunkikim.fooddelivery.domain.Order;
import com.hunkikim.fooddelivery.dto.order.OrderCreateServiceDto;
import com.hunkikim.fooddelivery.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultOrderService implements OrderService {

    private final OrderRepository orderRepository;

    public DefaultOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(OrderCreateServiceDto orderCreateServiceDto) {
        return orderRepository.insert(new Order(orderCreateServiceDto.getOrderId(),orderCreateServiceDto.getPhoneNumber(),orderCreateServiceDto.getAddress(),orderCreateServiceDto.getPostcode(),orderCreateServiceDto.getOrderStatus(),orderCreateServiceDto.getOrderItems(),orderCreateServiceDto.getCreatedAt(),orderCreateServiceDto.getUpdatedAt()));
    }
}
