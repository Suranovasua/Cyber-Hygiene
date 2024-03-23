package com.example.cyberhygien.controller;

import com.example.cyberhygien.dto.ProgressTrackingDTO;
import com.example.cyberhygien.service.ProgressTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/progress-trackings")
public class ProgressTrackingController {

    @Autowired
    private ProgressTrackingService progressTrackingService;

    @GetMapping
    public ResponseEntity<List<ProgressTrackingDTO>> getAllProgressTrackings() {
        List<ProgressTrackingDTO> progressTrackings = progressTrackingService.getAllProgressTrackings();
        return ResponseEntity.ok(progressTrackings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgressTrackingDTO> getProgressTrackingById(@PathVariable Long id) {
        ProgressTrackingDTO progressTracking = progressTrackingService.getProgressTrackingById(id);
        return ResponseEntity.ok(progressTracking);
    }

    @PostMapping
    public ResponseEntity<ProgressTrackingDTO> createProgressTracking(@Valid @RequestBody ProgressTrackingDTO progressTrackingDTO) {
        ProgressTrackingDTO createdProgressTracking = progressTrackingService.createProgressTracking(progressTrackingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProgressTracking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgressTrackingDTO> updateProgressTracking(@PathVariable Long id, @Valid @RequestBody ProgressTrackingDTO progressTrackingDTO) {
        ProgressTrackingDTO updatedProgressTracking = progressTrackingService.updateProgressTracking(id, progressTrackingDTO);
        return ResponseEntity.ok(updatedProgressTracking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgressTracking(@PathVariable Long id) {
        progressTrackingService.deleteProgressTracking(id);
        return ResponseEntity.noContent().build();
    }
}
