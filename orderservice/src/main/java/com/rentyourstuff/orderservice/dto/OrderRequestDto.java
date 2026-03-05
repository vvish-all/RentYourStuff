package com.rentyourstuff.orderservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDto {

    @NotNull
    private UUID userId;
    @NotNull
    private UUID productId;
    @NotBlank(message = "Product Name is mandatory")
    private String productName;
    @Positive(message = "Quantity is Mandatory")
    private Integer quantity;
}
