package com.roplant.sep.repository;
import com.roplant.sep.model.Representative;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RepresentativeRepository extends JpaRepository<Representative, UUID> {
    Representative findByCnic(String cnic);
}