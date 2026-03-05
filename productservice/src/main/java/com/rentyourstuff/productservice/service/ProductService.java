package com.rentyourstuff.productservice.service;

import com.rentyourstuff.productservice.dto.ProductRequestDto;
import com.rentyourstuff.productservice.dto.ProductResponseDto;
import com.rentyourstuff.productservice.entity.Product;
import com.rentyourstuff.productservice.repository.ProductRepository;
import com.rentyourstuff.productservice.util.ProductMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public final ProductMapper productMapper;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Product not found with id: " + id
                ));
    }
    

    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        //set ownerId from userId from gateway
        Product saved = productRepository.save(productMapper.toProductEntity(productRequestDto));
        return productMapper.toProductResponseDto(saved);
    }

    public ProductResponseDto updateProduct(ProductRequestDto productRequestDto) {
        Product productInDb = productRepository.findById(productRequestDto.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Product not found with id: " + productRequestDto.getId()
                ));

        Product updatedProduct = productMapper.updateProduct(productRequestDto, productInDb);
        Product saved = productRepository.save(updatedProduct);
        return productMapper.toProductResponseDto(saved);
    }

    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }
    

    public List<Product> getProductsByOwnerId(UUID ownerId) {
        return productRepository.findByOwnerId(ownerId).
                orElseThrow(() -> new EntityNotFoundException(
                        "Owner not found with id: " + ownerId
                ));
    }

}
