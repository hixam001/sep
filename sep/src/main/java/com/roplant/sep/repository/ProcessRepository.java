package com.roplant.sep.repository;

import com.roplant.sep.model.Process;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProcessRepository extends JpaRepository<Process, UUID> {
    // Find all processes for a specific production batch, ordered by sequence
    List<Process> findByProductionProductionIDOrderBySequenceOrderAsc(UUID productionId);
}