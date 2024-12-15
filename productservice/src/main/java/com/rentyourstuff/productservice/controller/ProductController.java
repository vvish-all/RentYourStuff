package com.rentyourstuff.productservice.controller;

import com.rentyourstuff.productservice.entity.Product;
import com.rentyourstuff.productservice.service.ProductService;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService; 
    
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(savedProduct);
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
    
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Product>> getProductsByOwnerId(@PathVariable Long ownerId) {
        Optional<List<Product>> products = productService.getProductsByOwnerId(ownerId);
        if (products.isPresent() && !products.get().isEmpty()) {
            return ResponseEntity.ok(products.get());
        } else {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body(null);
        }
    }
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
    
    
}
