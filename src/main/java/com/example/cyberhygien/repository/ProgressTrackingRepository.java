package com.example.cyberhygien.repository;

import com.example.cyberhygien.entity.ProgressTracking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressTrackingRepository extends JpaRepository<ProgressTracking, Long> {
}
