package com.example.fruithub.repository;

import com.example.fruithub.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StatusRepository extends JpaRepository<Status, UUID> {
    Optional<Status> findById(UUID uuid);

    @Query("SELECT u FROM Status u WHERE u.name = ?1")
    Optional<Status> findByName(String name);
}
