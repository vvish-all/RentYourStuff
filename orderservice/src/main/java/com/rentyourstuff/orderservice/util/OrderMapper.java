package com.rentyourstuff.orderservice.util;

import com.rentyourstuff.orderservice.dto.OrderRequestDto;
import com.rentyourstuff.orderservice.dto.OrderResponseDto;
import com.rentyourstuff.orderservice.entity.Order;
import com.rentyourstuff.orderservice.enums.OrderStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderMapper {

    public Order toOrderEntity (OrderRequestDto dto) {
        return Order.builder()
                .userId(dto.getUserId())
                .productId(dto.getProductId())
                .productName(dto.getProductName())
                .quantity(dto.getQuantity())
                .totalAmount(BigDecimal.ONE) //get unit price from Product
                .status(OrderStatus.PENDING).build();
    }

    public OrderResponseDto toOrderResponseDto (Order order){
        return new OrderResponseDto(
                order.getId(),
                order.getUserId(),
                order.getProductId(),
                order.getProductName(),
                order.getQuantity(),
                order.getTotalAmount(),
                order.getCreatedAt(),
                order.getUpdatedAt(),
                order.getVersion(),
                order.getStatus()
        );
    }

    public Order updateOrder (Order order, OrderRequestDto reqDto) {
        if(reqDto.getProductName() != null &&  !reqDto.getProductName().isBlank()){
            order.setProductName(reqDto.getProductName());
        }
        if(reqDto.getQuantity() != null && reqDto.getQuantity() >= 1){
            order.setQuantity(reqDto.getQuantity());
        }
        return order;
    }

}
