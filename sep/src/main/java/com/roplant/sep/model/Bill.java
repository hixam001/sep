package com.roplant.sep.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID billID;

    // One Bill corresponds to one specific Refill Request
    @OneToOne
    @JoinColumn(name = "refill_request_id")
    private RefillRequest refillRequest;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "representative_id")
    private Representative representative;

    private BigDecimal totalAmount;
    private LocalDateTime billingDate;

    // PAID, UNPAID
    private String paymentStatus;

    public UUID getBillID() { return billID; }
    public void setBillID(UUID billID) { this.billID = billID; }

    public RefillRequest getRefillRequest() { return refillRequest; }
    public void setRefillRequest(RefillRequest refillRequest) { this.refillRequest = refillRequest; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Representative getRepresentative() { return representative; }
    public void setRepresentative(Representative representative) { this.representative = representative; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public LocalDateTime getBillingDate() { return billingDate; }
    public void setBillingDate(LocalDateTime billingDate) { this.billingDate = billingDate; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
}