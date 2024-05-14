package com.example.cyberhygien.exceptionhandler;

import com.example.cyberhygien.dto.ProgressTrackingDTO;
import com.example.cyberhygien.repository.LessonRepository;
import com.example.cyberhygien.repository.ProgressTrackingRepository;
import com.example.cyberhygien.service.ProgressTrackingService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProgressTrackingServiceExceptionHandlingTest {

    @Mock
    private ProgressTrackingRepository progressTrackingRepository;

    @Mock
    private LessonRepository lessonRepository;

    @Mock
    private UserAccountRepository userAccountRepository;

    @InjectMocks
    private ProgressTrackingService progressTrackingService;

    @Test
    public void testCreateProgressTrackingLessonNotFound() {
        ProgressTrackingDTO progressTrackingDTO = new ProgressTrackingDTO();
        progressTrackingDTO.setLessonId(1L);
        progressTrackingDTO.setUserAccountId(1L);
        when(lessonRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> progressTrackingService.createProgressTracking(progressTrackingDTO));
    }

    @Test
    public void testCreateProgressTrackingUserAccountNotFound() {

        ProgressTrackingDTO progressTrackingDTO = new ProgressTrackingDTO();
        progressTrackingDTO.setLessonId(1L);
        progressTrackingDTO.setUserAccountId(1L);
        when(lessonRepository.existsById(anyLong())).thenReturn(true);
        when(userAccountRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> progressTrackingService.createProgressTracking(progressTrackingDTO));
    }


}
