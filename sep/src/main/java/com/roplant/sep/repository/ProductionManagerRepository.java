package com.roplant.sep.repository;

import com.roplant.sep.model.ProductionManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ProductionManagerRepository extends JpaRepository<ProductionManager, UUID> {
}