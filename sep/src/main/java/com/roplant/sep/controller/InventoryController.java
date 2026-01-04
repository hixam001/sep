package com.roplant.sep.controller;

import com.roplant.sep.model.EquipmentTransaction;
import com.roplant.sep.model.Inventory;
import com.roplant.sep.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired private InventoryService service;

    @GetMapping
    public List<Inventory> getAllItems() {
        return service.getAllItems();
    }
    
    @PostMapping
    public Inventory createItem(@RequestBody Inventory item) {
        return service.createItem(item);
    }

    @PostMapping("/transactions")
    public EquipmentTransaction createTransaction(@RequestBody Map<String, Object> payload) {
        String type = (String) payload.get("type");
        UUID stockMgrId = UUID.fromString((String) payload.get("requesterId"));
        UUID itemId = UUID.fromString((String) payload.get("itemId"));
        int qty = (int) payload.get("quantity");
        UUID providerId = UUID.fromString((String) payload.get("providerId"));

        if ("PROVIDE".equals(type)) {
            return service.createProviderTransaction(stockMgrId, providerId, itemId, qty);
        } else {
            return service.createChangeTransaction(stockMgrId, providerId, itemId, qty);
        }
    }

    @PostMapping("/transactions/{id}/complete")
    public EquipmentTransaction completeTransaction(@PathVariable UUID id) {
        return service.completeTransaction(id);
    }
}