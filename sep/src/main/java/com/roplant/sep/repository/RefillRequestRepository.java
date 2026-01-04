package com.roplant.sep.repository;

import com.roplant.sep.model.RefillRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface RefillRequestRepository extends JpaRepository<RefillRequest, UUID> {
    // Find all requests for a specific customer
    List<RefillRequest> findByCustomerCustomerID(UUID customerId);
    
    // Find all UNPAID requests
    List<RefillRequest> findByPaymentStatus(String paymentStatus);
}