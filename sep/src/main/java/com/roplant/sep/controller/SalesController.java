package com.roplant.sep.controller;

import com.roplant.sep.model.RefillRequest;
import com.roplant.sep.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.Map;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

    @Autowired private SalesService service;

    @PostMapping("/refill")
    public RefillRequest requestRefill(@RequestBody Map<String, Object> payload) {
        UUID custId = UUID.fromString((String) payload.get("customerId"));
        int bottles = (int) payload.get("bottleCount");
        return service.createRefill(custId, bottles);
    }

    @PostMapping("/{id}/pay")
    public RefillRequest pay(@PathVariable UUID id, @RequestParam UUID representativeId) {
        return service.payBill(id, representativeId);
    }
}