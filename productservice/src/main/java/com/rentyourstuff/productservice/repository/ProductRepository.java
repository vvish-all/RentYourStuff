package com.rentyourstuff.productservice.repository;

import com.rentyourstuff.productservice.entity.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Optional<List<Product>> findByOwnerId(Long ownerId);
    // Custom queries can be added here if needed
	
}
