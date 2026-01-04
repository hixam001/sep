package com.roplant.sep.service;

import com.roplant.sep.enums.TransactionType;
import com.roplant.sep.model.*;
import com.roplant.sep.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class InventoryService {

    @Autowired private InventoryRepository invRepo;
    @Autowired private EquipmentTransactionRepository transRepo;
    @Autowired private StockManagerRepository stockRepo;
    @Autowired private VendorRepository vendorRepo;
    @Autowired private LabourWorkerRepository labourRepo;

    public List<Inventory> getAllItems() {
        return invRepo.findAll();
    }

    public Inventory createItem(Inventory item) {
        return invRepo.save(item);
    }

    @Transactional
    public EquipmentTransaction createProviderTransaction(UUID stockMgrId, UUID vendorId, UUID itemId, int quantity) {
        StockManager mgr = stockRepo.findById(stockMgrId).orElseThrow();
        Vendor vendor = vendorRepo.findById(vendorId).orElseThrow();
        Inventory item = invRepo.findById(itemId).orElseThrow();

        EquipmentTransaction tx = new EquipmentTransaction();
        tx.setItem(item);
        tx.setRequester(mgr);
        tx.setVendorProvider(vendor);
        tx.setQuantity(quantity);
        tx.setType(TransactionType.PROVIDE);
        tx.setTransactionDate(LocalDateTime.now());
        tx.setStatus("PENDING");
        
        BigDecimal totalCost = item.getUnitPrice().multiply(BigDecimal.valueOf(quantity));
        tx.setCost(totalCost);

        return transRepo.save(tx);
    }

    @Transactional
    public EquipmentTransaction createChangeTransaction(UUID stockMgrId, UUID labourId, UUID itemId, int quantity) {
        StockManager mgr = stockRepo.findById(stockMgrId).orElseThrow();
        LabourWorker labour = labourRepo.findById(labourId).orElseThrow();
        Inventory item = invRepo.findById(itemId).orElseThrow();

        EquipmentTransaction tx = new EquipmentTransaction();
        tx.setItem(item);
        tx.setRequester(mgr);
        tx.setLabourProvider(labour);
        tx.setQuantity(quantity);
        tx.setType(TransactionType.CHANGE);
        tx.setTransactionDate(LocalDateTime.now());
        tx.setStatus("PENDING");
        
        // Example: Labour cost is 20% of item value
        BigDecimal labourCost = item.getUnitPrice().multiply(BigDecimal.valueOf(quantity)).multiply(BigDecimal.valueOf(0.2));
        tx.setCost(labourCost);

        return transRepo.save(tx);
    }
    
    @Transactional
    public EquipmentTransaction completeTransaction(UUID txId) {
        EquipmentTransaction tx = transRepo.findById(txId).orElseThrow();
        Inventory item = tx.getItem();
        
        if ("COMPLETED".equals(tx.getStatus())) return tx;

        if (tx.getType() == TransactionType.PROVIDE) {
            item.setQuantity(item.getQuantity() + tx.getQuantity());
            Vendor v = tx.getVendorProvider();
            v.setVendorBalance(v.getVendorBalance().add(tx.getCost()));
            vendorRepo.save(v);
        } else {
            item.setQuantity(item.getQuantity() - tx.getQuantity());
            LabourWorker l = tx.getLabourProvider();
            l.setLabourBalance(l.getLabourBalance().add(tx.getCost()));
            labourRepo.save(l);
        }

        invRepo.save(item);
        tx.setStatus("COMPLETED");
        tx.setEffectiveDate(LocalDateTime.now());
        return transRepo.save(tx);
    }
}