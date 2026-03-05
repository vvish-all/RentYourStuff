package com.rentyourstuff.orderservice.controller;

import com.rentyourstuff.orderservice.dto.OrderRequestDto;
import com.rentyourstuff.orderservice.dto.OrderResponseDto;
import com.rentyourstuff.orderservice.dto.OrderUpdateRequestDto;
import com.rentyourstuff.orderservice.dto.OrderUpdateResponseDto;
import com.rentyourstuff.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<?> placeOrder(@Valid @RequestBody OrderRequestDto requestDto) {
        OrderResponseDto responseDto = orderService.createOrder(requestDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/update-order")
    public ResponseEntity<?> updateOrderStatus(@Valid @RequestBody OrderUpdateRequestDto orderUpdateRequestDto) {
        OrderUpdateResponseDto orderUpdateResponseDto = orderService.updateOrder(orderUpdateRequestDto);
        return ResponseEntity.ok().body(orderUpdateResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable UUID id) {
        OrderResponseDto orderById = orderService.getOrderById(id);
        return ResponseEntity.ok().body(orderById);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getOrderByUserId(@PathVariable UUID id) {
        List<OrderResponseDto> ordersByUserId = orderService.getOrderByUserId(id);
        return ResponseEntity.ok().body(ordersByUserId);
    }

    @GetMapping("/health")
    public ResponseEntity<?> getHeath (){
        return ResponseEntity.ok().body("OK");
    }
}
