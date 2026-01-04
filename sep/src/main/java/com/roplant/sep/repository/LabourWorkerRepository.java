package com.roplant.sep.repository;

import com.roplant.sep.model.LabourWorker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface LabourWorkerRepository extends JpaRepository<LabourWorker, UUID> {
}