package com.example.fruithub.repository;

import com.example.fruithub.model.Category;
import com.example.fruithub.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAll();
    Optional<Product> findById(UUID uuid);
    Optional<Product> findByName(String name);
    void delete(Product existingProduct);
}
