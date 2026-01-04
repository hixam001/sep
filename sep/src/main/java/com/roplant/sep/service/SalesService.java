package com.roplant.sep.service;

import com.roplant.sep.enums.ProductionStatus;
import com.roplant.sep.model.*;
import com.roplant.sep.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SalesService {

    @Autowired private RefillRequestRepository refillRepo;
    @Autowired private ProductionRepository prodRepo;
    @Autowired private CustomerRepository custRepo;
    @Autowired private RepresentativeRepository repRepo;
    @Autowired private BillRepository billRepo;

    // UC-10: Request Refill
    @Transactional
    public RefillRequest createRefill(UUID customerId, int bottles) {
        // Check Water Availability
        boolean hasWater = prodRepo.findAll().stream()
                .anyMatch(p -> p.getStatus() == ProductionStatus.COMPLETED);
        
        if (!hasWater) throw new RuntimeException("No water available for refill");

        Customer cust = custRepo.findById(customerId).orElseThrow();
        
        RefillRequest req = new RefillRequest();
        req.setCustomer(cust);
        req.setBottleCount(bottles);
        req.setStatus("PENDING");
        req.setPaymentStatus("UNPAID");
        req.setTransactionDate(LocalDateTime.now());
        
        // Price Calculation (e.g., 50.00 per bottle)
        req.setTotalAmount(BigDecimal.valueOf(50).multiply(BigDecimal.valueOf(bottles)));
        
        return refillRepo.save(req);
    }

    // UC-14: Pay Bill & Update Records
    @Transactional
    public RefillRequest payBill(UUID reqId, UUID repId) {
        RefillRequest req = refillRepo.findById(reqId).orElseThrow();
        Representative rep = repRepo.findById(repId).orElseThrow();
        
        // 1. Financials: Increase Rep Balance
        rep.setRepresentativeBalance(rep.getRepresentativeBalance().add(req.getTotalAmount()));
        repRepo.save(rep);

        // 2. Create Permanent Bill Record (Audit Trail)
        Bill bill = new Bill();
        bill.setRefillRequest(req);
        bill.setCustomer(req.getCustomer());
        bill.setRepresentative(rep);
        bill.setTotalAmount(req.getTotalAmount());
        bill.setBillingDate(LocalDateTime.now());
        bill.setPaymentStatus("PAID");
        billRepo.save(bill);
        
        // 3. Update Request Status
        req.setRepresentative(rep);
        req.setPaymentStatus("PAID");
        req.setEffectiveDate(LocalDateTime.now());
        req.setStatus("FILLED");
        
        return refillRepo.save(req);
    }
}