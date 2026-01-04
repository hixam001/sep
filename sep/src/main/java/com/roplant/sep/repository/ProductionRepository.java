package com.roplant.sep.repository;

import com.roplant.sep.enums.ProductionStatus;
import com.roplant.sep.model.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProductionRepository extends JpaRepository<Production, UUID> {
    // Find all productions with a specific status (e.g., COMPLETED)
    List<Production> findByStatus(ProductionStatus status);
}