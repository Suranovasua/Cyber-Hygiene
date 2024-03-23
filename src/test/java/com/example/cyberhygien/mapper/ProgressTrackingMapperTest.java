package com.example.cyberhygien.mapper;

import com.example.cyberhygien.dto.ProgressTrackingDTO;
import com.example.cyberhygien.entity.Lesson;
import com.example.cyberhygien.entity.ProgressTracking;
import com.example.cyberhygien.entity.UserAccount;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProgressTrackingMapperTest {

    @Test
    public void testProgressTrackingToProgressTrackingDTO() {
        ProgressTracking progressTracking = new ProgressTracking();
        progressTracking.setId(1L);
        progressTracking.setUserAccount(new UserAccount(1L, "username", "email@example.com", "password", "role"));
        progressTracking.setLesson(new Lesson(1L, "Sample Title", "Sample Description", "Sample Text", "image.jpg"));
        progressTracking.setCompletionStatus(true);
        progressTracking.setCompletionDate(LocalDateTime.now());


        ProgressTrackingDTO progressTrackingDTO = ProgressTrackingMapper.INSTANCE.progressTrackingToProgressTrackingDTO(progressTracking);


        assertEquals(progressTracking.getId(), progressTrackingDTO.getId());
        assertEquals(progressTracking.getUserAccount().getUserId(), progressTrackingDTO.getUserAccountId());
        assertEquals(progressTracking.getLesson().getLessonId(), progressTrackingDTO.getLessonId());
        assertEquals(progressTracking.getCompletionStatus(), progressTrackingDTO.getCompletionStatus());
        assertEquals(progressTracking.getCompletionDate(), progressTrackingDTO.getCompletionDate());
    }

    @Test
    public void testProgressTrackingDTOToProgressTracking() {

        ProgressTrackingDTO progressTrackingDTO = new ProgressTrackingDTO();
        progressTrackingDTO.setId(1L);
        progressTrackingDTO.setUserAccountId(1L);
        progressTrackingDTO.setLessonId(1L);
        progressTrackingDTO.setCompletionStatus(true);
        progressTrackingDTO.setCompletionDate(LocalDateTime.now());


        ProgressTracking progressTracking = ProgressTrackingMapper.INSTANCE.progressTrackingDTOToProgressTracking(progressTrackingDTO);


        assertEquals(progressTrackingDTO.getId(), progressTracking.getId());
        assertEquals(progressTrackingDTO.getUserAccountId(), progressTracking.getUserAccount().getUserId());
        assertEquals(progressTrackingDTO.getLessonId(), progressTracking.getLesson().getLessonId());
        assertEquals(progressTrackingDTO.getCompletionStatus(), progressTracking.getCompletionStatus());
        assertEquals(progressTrackingDTO.getCompletionDate(), progressTracking.getCompletionDate());
    }
}
