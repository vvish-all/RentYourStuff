package com.rentyourstuff.productservice.controller;

import com.rentyourstuff.productservice.dto.ProductRequestDto;
import com.rentyourstuff.productservice.dto.ProductResponseDto;
import com.rentyourstuff.productservice.entity.Product;
import com.rentyourstuff.productservice.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping("/create-product")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequestDto product) {
        ProductResponseDto productResponseDto = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllProducts() {

        List<Product> allProducts = productService.getAllProducts();
        return ResponseEntity.ok().body(allProducts);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable UUID id) {
        Optional<Product> productById = productService.getProductById(id);
        if(productById.isPresent()){
            return ResponseEntity.ok().body(productById.get());
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Product>> getProductsByOwnerId(@PathVariable UUID ownerId) {
        Optional<List<Product>> products = productService.getProductsByOwnerId(ownerId);
        if (products.isPresent() && !products.get().isEmpty()) {
            return ResponseEntity.ok(products.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {// check the owner

        ProductResponseDto productResponseDto = productService.updateProduct(productRequestDto);
        return ResponseEntity.ok().body(productResponseDto);
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable UUID id) {

        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/health")
    public ResponseEntity<?> healthCheck(){
        return ResponseEntity.ok().build();
    }
    
}
