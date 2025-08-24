package com.example.fruithub.repository;

import com.example.fruithub.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CurrencyRepository extends JpaRepository<Currency, UUID> {
    boolean existsByName(String name);
}
