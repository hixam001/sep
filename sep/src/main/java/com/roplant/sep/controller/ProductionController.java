package com.roplant.sep.controller;

import com.roplant.sep.enums.ProductionStatus;
import com.roplant.sep.model.Production;
import com.roplant.sep.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/production")
public class ProductionController {

    @Autowired private ProductionService service;

    @PostMapping("/start")
    public Production start(@RequestParam UUID managerId) {
        return service.startProduction(managerId);
    }

    @PatchMapping("/{id}/status")
    public Production updateStatus(@PathVariable UUID id, @RequestBody Map<String, String> payload) {
        ProductionStatus status = ProductionStatus.valueOf(payload.get("status"));
        return service.updateStatus(id, status);
    }

    @GetMapping
    public List<Production> getAll() {
        return service.getAll();
    }
}