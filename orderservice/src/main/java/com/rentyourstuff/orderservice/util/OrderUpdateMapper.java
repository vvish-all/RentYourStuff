package com.rentyourstuff.orderservice.util;

import com.rentyourstuff.orderservice.dto.OrderUpdateResponseDto;
import com.rentyourstuff.orderservice.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderUpdateMapper {
    public OrderUpdateResponseDto toOrderUpdateResponseDto(Order order) {
        return OrderUpdateResponseDto.builder()
                .id(order.getId())
                .productId(order.getProductId())
                .productName(order.getProductName())
                .quantity(order.getQuantity())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .build();
    }
}
