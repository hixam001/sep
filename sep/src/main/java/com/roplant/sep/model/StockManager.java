package com.roplant.sep.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class StockManager extends BaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String authorizationLevel;

    // Tracks total owed to vendors
    private BigDecimal vendorBalance = BigDecimal.ZERO;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getAuthorizationLevel() { return authorizationLevel; }
    public void setAuthorizationLevel(String authorizationLevel) { this.authorizationLevel = authorizationLevel; }

    public BigDecimal getVendorBalance() { return vendorBalance; }
    public void setVendorBalance(BigDecimal vendorBalance) { this.vendorBalance = vendorBalance; }
}