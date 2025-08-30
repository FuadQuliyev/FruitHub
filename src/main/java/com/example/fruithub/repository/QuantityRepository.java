package com.example.fruithub.repository;

import com.example.fruithub.model.Quantity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface QuantityRepository extends JpaRepository<Quantity, UUID> {
    Optional<Quantity> findById(UUID uuid);
}
