package com.rentyourstuff.productservice.service;

import com.rentyourstuff.productservice.dto.AppUser;
import com.rentyourstuff.productservice.entity.Product;
import com.rentyourstuff.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${eureka.client.service-url.default-zone}")
    private String EUREKA_SERVICE_BASE_URL;           //"Http://localhost:8671/users"; // Eureka URI 

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            return productRepository.save(product);
        }
        return null;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    

    public Optional<List<Product>> getProductsByOwnerId(Long ownerId) {
        return productRepository.findByOwnerId(ownerId);
    }

    public AppUser getOwnerDetails(Long ownerId) {
        String url = EUREKA_SERVICE_BASE_URL + "/users/" + ownerId;
        return restTemplate.getForObject(url, AppUser.class); 
    }
}
