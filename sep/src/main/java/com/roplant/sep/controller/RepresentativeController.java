package com.roplant.sep.controller;

import com.roplant.sep.model.LabourWorker;
import com.roplant.sep.model.Representative;
import com.roplant.sep.model.Vendor;
import com.roplant.sep.repository.LabourWorkerRepository;
import com.roplant.sep.repository.RepresentativeRepository;
import com.roplant.sep.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/representatives")
public class RepresentativeController {

    @Autowired private RepresentativeRepository repRepo;
    @Autowired private VendorRepository vendorRepo;
    @Autowired private LabourWorkerRepository labourRepo;

    // UC-06: Pay Vendor (Generic)
    @PostMapping("/{repId}/pay/vendor/{vendorId}")
    @Transactional
    public ResponseEntity<String> payVendor(@PathVariable UUID repId, 
                                            @PathVariable UUID vendorId, 
                                            @RequestParam BigDecimal amount) {
        Representative rep = repRepo.findById(repId).orElseThrow();
        Vendor vendor = vendorRepo.findById(vendorId).orElseThrow();

        // 1. Decrease Rep Balance (Money Out)
        rep.setRepresentativeBalance(rep.getRepresentativeBalance().subtract(amount));
        
        // 2. Decrease Vendor Balance (Debt Paid)
        vendor.setVendorBalance(vendor.getVendorBalance().subtract(amount));

        repRepo.save(rep);
        vendorRepo.save(vendor);
        return ResponseEntity.ok("Payment to vendor successful");
    }

    // UC-09: Pay Labour (Generic)
    @PostMapping("/{repId}/pay/labour/{labourId}")
    @Transactional
    public ResponseEntity<String> payLabour(@PathVariable UUID repId, 
                                            @PathVariable UUID labourId, 
                                            @RequestParam BigDecimal amount) {
        Representative rep = repRepo.findById(repId).orElseThrow();
        LabourWorker labour = labourRepo.findById(labourId).orElseThrow();

        // 1. Decrease Rep Balance
        rep.setRepresentativeBalance(rep.getRepresentativeBalance().subtract(amount));
        
        // 2. Decrease Labour Balance (Debt Paid)
        labour.setLabourBalance(labour.getLabourBalance().subtract(amount));

        repRepo.save(rep);
        labourRepo.save(labour);
        return ResponseEntity.ok("Payment to labour successful");
    }
}