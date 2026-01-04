package com.roplant.sep.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.roplant.sep.enums.ProductionStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productionID;

    @Enumerated(EnumType.STRING)
    private ProductionStatus status;
    
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    // Optional batch number assigned when production starts
    private Long batchNumber; // Optional batch number assigned when production starts

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private ProductionManager manager;
    
    public UUID getProductionID() { return productionID; }
    public void setProductionID(UUID productionID) { this.productionID = productionID; }
    
    public ProductionStatus getStatus() { return status; }
    public void setStatus(ProductionStatus status) { this.status = status; }
    
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    
    public Long getBatchNumber() { return batchNumber; }
    public void setBatchNumber(Long batchNumber) { this.batchNumber = batchNumber; }
    
    public ProductionManager getManager() { return manager; }
    public void setManager(ProductionManager manager) { this.manager = manager; }
}