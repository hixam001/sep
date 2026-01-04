package com.roplant.sep.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Customer extends BaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID customerID;

    private String status;
    private BigDecimal customerBalance = BigDecimal.ZERO;

    // File Path String (Not BLOB)
    private String clientImagePath;

    public UUID getCustomerID() { return customerID; }
    public void setCustomerID(UUID customerID) { this.customerID = customerID; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public BigDecimal getCustomerBalance() { return customerBalance; }
    public void setCustomerBalance(BigDecimal customerBalance) { this.customerBalance = customerBalance; }

    public String getClientImagePath() { return clientImagePath; }
    public void setClientImagePath(String clientImagePath) { this.clientImagePath = clientImagePath; }
}