package com.rentyourstuff.orderservice.dto;

import com.rentyourstuff.orderservice.enums.OrderStatus;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderUpdateRequestDto {
    @NotNull(message = "OrderId is mandatory")
    private UUID id;
    @NotNull(message = "userId is mandatory")
    private UUID userId;
    private Integer quantity;
    private OrderStatus status;
}
