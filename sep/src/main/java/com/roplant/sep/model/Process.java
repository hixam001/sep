package com.roplant.sep.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Process {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID processID;

    private String name; // e.g., "Filtration"
    private String description;
    private Integer sequenceOrder; // 1, 2, 3

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "production_id")
    private Production production;

    public UUID getProcessID() { return processID; }
    public void setProcessID(UUID processID) { this.processID = processID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getSequenceOrder() { return sequenceOrder; }
    public void setSequenceOrder(Integer sequenceOrder) { this.sequenceOrder = sequenceOrder; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public Production getProduction() { return production; }
    public void setProduction(Production production) { this.production = production; }
}