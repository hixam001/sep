package com.roplant.sep.repository;

import com.roplant.sep.model.EquipmentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface EquipmentTransactionRepository extends JpaRepository<EquipmentTransaction, UUID> {
    // Find all pending transactions
    List<EquipmentTransaction> findByStatus(String status);
}