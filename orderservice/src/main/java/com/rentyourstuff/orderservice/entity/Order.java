package com.rentyourstuff.orderservice.entity;

import java.math.BigDecimal;
import java.util.UUID;

import com.rentyourstuff.orderservice.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
	@Column(nullable = false, updatable = false)
	private UUID userId;
	@Column(nullable = false, updatable = false)
	private UUID productId;
    private String productName;
	@Column(nullable = false)
	@Positive
    private Integer quantity;
	@Column(nullable = false)
    private BigDecimal totalAmount;
	private OrderStatus status;
}
