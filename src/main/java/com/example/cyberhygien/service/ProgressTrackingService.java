package com.example.cyberhygien.service;

import com.example.cyberhygien.dto.ProgressTrackingDTO;
import com.example.cyberhygien.entity.ProgressTracking;
import com.example.cyberhygien.mapper.ProgressTrackingMapper;
import com.example.cyberhygien.repository.ProgressTrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgressTrackingService {

    @Autowired
    private ProgressTrackingRepository progressTrackingRepository;

    public List<ProgressTrackingDTO> getAllProgressTrackings() {
        List<ProgressTracking> progressTrackings = progressTrackingRepository.findAll();
        return progressTrackings.stream()
                .map(ProgressTrackingMapper.INSTANCE::progressTrackingToProgressTrackingDTO)
                .collect(Collectors.toList());
    }

    public ProgressTrackingDTO getProgressTrackingById(Long id) {
        ProgressTracking progressTracking = progressTrackingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Progress tracking not found with id: " + id));
        return ProgressTrackingMapper.INSTANCE.progressTrackingToProgressTrackingDTO(progressTracking);
    }

    public ProgressTrackingDTO createProgressTracking(ProgressTrackingDTO progressTrackingDTO) {
        ProgressTracking progressTracking = ProgressTrackingMapper.INSTANCE.progressTrackingDTOToProgressTracking(progressTrackingDTO);
        return ProgressTrackingMapper.INSTANCE.progressTrackingToProgressTrackingDTO(progressTrackingRepository.save(progressTracking));
    }

    public ProgressTrackingDTO updateProgressTracking(Long id, ProgressTrackingDTO progressTrackingDTO) {

        ProgressTracking existingProgressTracking = progressTrackingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Progress tracking not found with id: " + id));


        existingProgressTracking.setCompletionStatus(progressTrackingDTO.getCompletionStatus());
        existingProgressTracking.setCompletionDate(progressTrackingDTO.getCompletionDate());


        ProgressTracking updatedProgressTracking = progressTrackingRepository.save(existingProgressTracking);

        return ProgressTrackingMapper.INSTANCE.progressTrackingToProgressTrackingDTO(updatedProgressTracking);
    }


    public void deleteProgressTracking(Long id) {
        if (!progressTrackingRepository.existsById(id)) {
            throw new EntityNotFoundException("Progress tracking not found with id: " + id);
        }
        progressTrackingRepository.deleteById(id);
    }
}
