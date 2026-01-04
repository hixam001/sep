package com.roplant.sep.model;

import com.roplant.sep.enums.TransactionType;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class EquipmentTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Inventory item;

    @ManyToOne
    private StockManager requester;

    // Can be Vendor OR Labour
    @ManyToOne
    private Vendor vendorProvider;

    @ManyToOne
    private LabourWorker labourProvider;

    private Integer quantity;
    private BigDecimal cost;

    @Enumerated(EnumType.STRING)
    private TransactionType type; // PROVIDE or CHANGE

    private String status; // PENDING, COMPLETED
    private LocalDateTime transactionDate;
    private LocalDateTime effectiveDate;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Inventory getItem() { return item; }
    public void setItem(Inventory item) { this.item = item; }

    public StockManager getRequester() { return requester; }
    public void setRequester(StockManager requester) { this.requester = requester; }

    public Vendor getVendorProvider() { return vendorProvider; }
    public void setVendorProvider(Vendor vendorProvider) { this.vendorProvider = vendorProvider; }

    public LabourWorker getLabourProvider() { return labourProvider; }
    public void setLabourProvider(LabourWorker labourProvider) { this.labourProvider = labourProvider; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BigDecimal getCost() { return cost; }
    public void setCost(BigDecimal cost) { this.cost = cost; }

    public TransactionType getType() { return type; }
    public void setType(TransactionType type) { this.type = type; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }

    public LocalDateTime getEffectiveDate() { return effectiveDate; }
    public void setEffectiveDate(LocalDateTime effectiveDate) { this.effectiveDate = effectiveDate; }
}