package com.roplant.sep.repository;

import com.roplant.sep.model.StockManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface StockManagerRepository extends JpaRepository<StockManager, UUID> {
}