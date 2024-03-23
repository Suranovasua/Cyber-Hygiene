package com.example.cyberhygien.controller;

import com.example.cyberhygien.dto.ProgressTrackingDTO;
import com.example.cyberhygien.service.ProgressTrackingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgressTrackingControllerTest {

    @Mock
    private ProgressTrackingService progressTrackingService;

    @InjectMocks
    private ProgressTrackingController progressTrackingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllProgressTrackings() {

        List<ProgressTrackingDTO> progressTrackings = new ArrayList<>();
        when(progressTrackingService.getAllProgressTrackings()).thenReturn(progressTrackings);


        ResponseEntity<List<ProgressTrackingDTO>> responseEntity = progressTrackingController.getAllProgressTrackings();


        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(progressTrackings, responseEntity.getBody());
    }

    @Test
    void testGetProgressTrackingById() {

        Long id = 1L;
        ProgressTrackingDTO progressTrackingDTO = new ProgressTrackingDTO();
        when(progressTrackingService.getProgressTrackingById(id)).thenReturn(progressTrackingDTO);


        ResponseEntity<ProgressTrackingDTO> responseEntity = progressTrackingController.getProgressTrackingById(id);


        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(progressTrackingDTO, responseEntity.getBody());
    }
    @Test
    void testCreateProgressTracking() {

        ProgressTrackingDTO progressTrackingDTO = new ProgressTrackingDTO();
        when(progressTrackingService.createProgressTracking(any(ProgressTrackingDTO.class))).thenReturn(progressTrackingDTO);


        ResponseEntity<ProgressTrackingDTO> responseEntity = progressTrackingController.createProgressTracking(progressTrackingDTO);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(progressTrackingDTO, responseEntity.getBody());
    }

}
