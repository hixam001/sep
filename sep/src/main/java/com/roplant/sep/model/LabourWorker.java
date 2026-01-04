package com.roplant.sep.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class LabourWorker extends BaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String authorizationLevel;
    private BigDecimal labourBalance = BigDecimal.ZERO; // Amount owed to this worker

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getAuthorizationLevel() { return authorizationLevel; }
    public void setAuthorizationLevel(String authorizationLevel) { this.authorizationLevel = authorizationLevel; }

    public BigDecimal getLabourBalance() { return labourBalance; }
    public void setLabourBalance(BigDecimal labourBalance) { this.labourBalance = labourBalance; }
}