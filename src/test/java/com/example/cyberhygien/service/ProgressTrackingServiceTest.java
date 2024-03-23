package com.example.cyberhygien.service;

import com.example.cyberhygien.dto.ProgressTrackingDTO;
import com.example.cyberhygien.entity.ProgressTracking;
import com.example.cyberhygien.mapper.ProgressTrackingMapper;
import com.example.cyberhygien.repository.ProgressTrackingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProgressTrackingServiceTest {

    @Mock
    private ProgressTrackingRepository progressTrackingRepository;

    @InjectMocks
    private ProgressTrackingService progressTrackingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllProgressTrackings() {
        // Given
        List<ProgressTracking> progressTrackings = new ArrayList<>();
        when(progressTrackingRepository.findAll()).thenReturn(progressTrackings);

        // When
        List<ProgressTrackingDTO> result = progressTrackingService.getAllProgressTrackings();

        // Then
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void testGetProgressTrackingById() {
        // Given
        Long id = 1L;
        ProgressTracking progressTracking = new ProgressTracking();
        progressTracking.setId(id);
        when(progressTrackingRepository.findById(id)).thenReturn(Optional.of(progressTracking));

        // When
        ProgressTrackingDTO result = progressTrackingService.getProgressTrackingById(id);

        // Then
        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    void testGetProgressTrackingById_EntityNotFoundException() {
        // Given
        Long id = 1L;
        when(progressTrackingRepository.findById(id)).thenReturn(Optional.empty());

        // Then
        assertThrows(EntityNotFoundException.class, () -> progressTrackingService.getProgressTrackingById(id));
    }
    @Test
    void testCreateProgressTracking() {
        // Given
        ProgressTrackingDTO progressTrackingDTO = new ProgressTrackingDTO();
        progressTrackingDTO.setCompletionStatus(true);
        progressTrackingDTO.setCompletionDate(LocalDateTime.now());
        ProgressTracking progressTracking = ProgressTrackingMapper.INSTANCE.progressTrackingDTOToProgressTracking(progressTrackingDTO);
        when(progressTrackingRepository.save(any(ProgressTracking.class))).thenReturn(progressTracking);

        // When
        ProgressTrackingDTO result = progressTrackingService.createProgressTracking(progressTrackingDTO);

        // Then
        assertNotNull(result);
        assertTrue(result.getCompletionStatus());
        assertEquals(progressTrackingDTO.getCompletionDate(), result.getCompletionDate());
    }

    @Test
    void testUpdateProgressTracking() {

        Long id = 1L;
        ProgressTrackingDTO progressTrackingDTO = new ProgressTrackingDTO();
        progressTrackingDTO.setCompletionStatus(true);
        progressTrackingDTO.setCompletionDate(LocalDateTime.now());
        ProgressTracking existingProgressTracking = new ProgressTracking();
        existingProgressTracking.setId(id);
        when(progressTrackingRepository.findById(id)).thenReturn(Optional.of(existingProgressTracking));
        when(progressTrackingRepository.save(any(ProgressTracking.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ProgressTrackingDTO result = progressTrackingService.updateProgressTracking(id, progressTrackingDTO);


        assertNotNull(result);
        assertEquals(id, result.getId());
        assertTrue(result.getCompletionStatus());
        assertEquals(progressTrackingDTO.getCompletionDate(), result.getCompletionDate());
    }

    @Test
    void testDeleteProgressTracking() {

        Long id = 1L;
        when(progressTrackingRepository.existsById(id)).thenReturn(true);

        progressTrackingService.deleteProgressTracking(id);

        verify(progressTrackingRepository, times(1)).deleteById(id);
    }
}
