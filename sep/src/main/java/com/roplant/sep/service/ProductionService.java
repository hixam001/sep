package com.roplant.sep.service;

import com.roplant.sep.enums.ProductionStatus;
import com.roplant.sep.model.Process;
import com.roplant.sep.model.Production;
import com.roplant.sep.model.ProductionManager;
import com.roplant.sep.repository.ProcessRepository;
import com.roplant.sep.repository.ProductionManagerRepository;
import com.roplant.sep.repository.ProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ProductionService {

    @Autowired private ProductionRepository prodRepo;
    @Autowired private ProductionManagerRepository managerRepo;
    @Autowired private ProcessRepository processRepo;

    // UC-01: Start Water Extraction
    @Transactional
    public Production startProduction(UUID managerId) {
        ProductionManager manager = managerRepo.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager not found"));

        Production prod = new Production();
        prod.setManager(manager);
        prod.setStartTime(LocalDateTime.now());
        prod.setStatus(ProductionStatus.EXTRACTING);
        // Simple batch number generation logic
        prod.setBatchNumber(System.currentTimeMillis() / 1000); 
        
        return prodRepo.save(prod);
    }

    // UC-02, UC-03, UC-04: Update Status & Log Process
    @Transactional
    public Production updateStatus(UUID productionId, ProductionStatus newStatus) {
        Production prod = prodRepo.findById(productionId)
                .orElseThrow(() -> new RuntimeException("Production not found"));
        
        // 1. Update Main Production Status
        prod.setStatus(newStatus);
        if (newStatus == ProductionStatus.COMPLETED) {
            prod.setEndTime(LocalDateTime.now());
        }
        Production savedProd = prodRepo.save(prod);

        // 2. Create Process Log Entry
        Process proc = new Process();
        proc.setProduction(savedProd);
        proc.setName(newStatus.name()); // e.g., "FILTERING"
        proc.setDescription("Status changed to " + newStatus);
        proc.setStartTime(LocalDateTime.now());
        // Using enum ordinal as sequence (0=EXTRACTING, 1=FILTERING...)
        proc.setSequenceOrder(newStatus.ordinal()); 
        
        processRepo.save(proc);

        return savedProd;
    }
    
    public List<Production> getAll() {
        return prodRepo.findAll();
    }
}