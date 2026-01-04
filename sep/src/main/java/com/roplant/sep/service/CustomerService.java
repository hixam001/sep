package com.roplant.sep.service;

import com.roplant.sep.model.Customer;
import com.roplant.sep.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private final Path fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();

    public CustomerService() {
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String uploadClientImage(UUID customerId, MultipartFile file) {
        // Check if customer exists
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + customerId));

        // Normalize file name
        String fileName = "customer_" + customerId + "_" + file.getOriginalFilename();

        try {
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // Update Customer record
            String filePath = targetLocation.toString();
            customer.setClientImagePath(filePath);
            customerRepository.save(customer);

            return filePath;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
}
