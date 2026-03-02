package com.rentyourstuff.productservice.util;

import com.rentyourstuff.productservice.dto.ProductRequestDto;
import com.rentyourstuff.productservice.dto.ProductResponseDto;
import com.rentyourstuff.productservice.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    // ProductRequestDto → Product
    public Product toProductEntity(ProductRequestDto dto) {
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .ownerId(dto.getOwnerId())
                // id, createdAt, updatedAt, version are auto-managed
                .build();
    }

    // Product → ProductResponseDto
    public ProductResponseDto toProductResponseDto(Product product) {
        return new ProductResponseDto(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getOwnerId()
        );
    }

    // Product → ProductRequestDto
    public ProductRequestDto toProductRequestDto(Product product) {
        return new ProductRequestDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getOwnerId()
        );
    }

    // Update existing Product — only non-null fields (PATCH behavior)
    public Product updateProduct(ProductRequestDto dto, Product productInDb) {
        if (dto.getName() != null && !dto.getName().isBlank()) {
            productInDb.setName(dto.getName());
        }
        if (dto.getDescription() != null && !dto.getDescription().isBlank()) {
            productInDb.setDescription(dto.getDescription());
        }
        if (dto.getPrice() != null) {
            productInDb.setPrice(dto.getPrice());
        }
        if (dto.getOwnerId() != null) {
            productInDb.setOwnerId(dto.getOwnerId());
        }
        return productInDb;
    }
}