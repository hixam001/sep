package com.roplant.sep.controller;

import com.roplant.sep.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/{id}/image")
    public ResponseEntity<?> uploadImage(@PathVariable UUID id, @RequestParam("file") MultipartFile file) {
        try {
            String path = customerService.uploadClientImage(id, file);
            return ResponseEntity.ok("Image uploaded successfully to: " + path);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to upload image: " + e.getMessage());
        }
    }
}
