package com.rentyourstuff.productservice.repository;

import com.rentyourstuff.productservice.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

	Optional<List<Product>> findByOwnerId(UUID ownerId);

}
