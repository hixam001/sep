package com.roplant.sep.repository;

import com.roplant.sep.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, UUID> {
    // Helper to find item by name
    Inventory findByName(String name);
}