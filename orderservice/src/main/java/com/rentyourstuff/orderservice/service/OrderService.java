package com.rentyourstuff.orderservice.service;

import com.rentyourstuff.orderservice.dto.OrderRequestDto;
import com.rentyourstuff.orderservice.dto.OrderResponseDto;
import com.rentyourstuff.orderservice.dto.OrderUpdateRequestDto;
import com.rentyourstuff.orderservice.dto.OrderUpdateResponseDto;
import com.rentyourstuff.orderservice.entity.Order;
import com.rentyourstuff.orderservice.enums.OrderStatus;
import com.rentyourstuff.orderservice.repository.OrderRepository;
import com.rentyourstuff.orderservice.util.OrderMapper;
import com.rentyourstuff.orderservice.util.OrderUpdateMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final OrderUpdateMapper orderUpdateMapper;

    public OrderResponseDto createOrder(OrderRequestDto requestDto) {
        Order orderEntity = orderMapper.toOrderEntity(requestDto);
        Order saved = orderRepository.save(orderEntity);
        return orderMapper.toOrderResponseDto(saved);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderResponseDto getOrderById(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "No order found with id: " + id
                )
        );
        return orderMapper.toOrderResponseDto(order);
    }

    public OrderUpdateResponseDto updateOrder(OrderUpdateRequestDto orderUpdateRequestDto) {
        Order orderInDb = orderRepository.findById(orderUpdateRequestDto.getId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Order not found with id: " + orderUpdateRequestDto.getId()
                        )
                );
        Order updatedOrder = updateFetchedOrder(orderInDb, orderUpdateRequestDto);
        Order saved = orderRepository.save(updatedOrder);
        return orderUpdateMapper.toOrderUpdateResponseDto(saved);
    }

    private Order updateFetchedOrder(Order order, OrderUpdateRequestDto requestDto) {

        if(requestDto.getQuantity() != null && requestDto.getQuantity() >= 0){
            order.setQuantity(requestDto.getQuantity());
        }
        if(!order.getStatus().equals(OrderStatus.CANCELLED) && requestDto.getStatus() != null){
            order.setStatus(requestDto.getStatus());
        }
        return order;
    }

    public List<OrderResponseDto> getOrderByUserId(UUID id) {
        List<Order> ordersByUserId = orderRepository.findByUserId(id);
        return ordersByUserId.stream()
                .map(orderMapper::toOrderResponseDto)
                .toList();
    }
}
