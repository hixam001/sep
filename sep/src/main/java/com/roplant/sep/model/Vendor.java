package com.roplant.sep.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Vendor extends BaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String status;
    private BigDecimal vendorBalance = BigDecimal.ZERO; // Amount owed to this vendor

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public BigDecimal getVendorBalance() { return vendorBalance; }
    public void setVendorBalance(BigDecimal vendorBalance) { this.vendorBalance = vendorBalance; }
}