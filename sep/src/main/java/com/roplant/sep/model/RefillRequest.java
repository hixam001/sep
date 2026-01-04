package com.roplant.sep.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class RefillRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID requestID;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "representative_id")
    private Representative representative;

    private Integer bottleCount;
    private BigDecimal totalAmount;
    private String status; // PENDING, FILLED
    private String paymentStatus; // UNPAID, PAID

    private LocalDateTime transactionDate; // T-Date
    private LocalDateTime effectiveDate;   // E-Date

    public UUID getRequestID() { return requestID; }
    public void setRequestID(UUID requestID) { this.requestID = requestID; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Representative getRepresentative() { return representative; }
    public void setRepresentative(Representative representative) { this.representative = representative; }

    public Integer getBottleCount() { return bottleCount; }
    public void setBottleCount(Integer bottleCount) { this.bottleCount = bottleCount; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public LocalDateTime getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }

    public LocalDateTime getEffectiveDate() { return effectiveDate; }
    public void setEffectiveDate(LocalDateTime effectiveDate) { this.effectiveDate = effectiveDate; }
}