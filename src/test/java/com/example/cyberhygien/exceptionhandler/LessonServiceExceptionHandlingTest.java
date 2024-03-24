package com.example.cyberhygien.exceptionhandler;

import com.example.cyberhygien.dto.LessonDTO;
import com.example.cyberhygien.entity.Lesson;
import com.example.cyberhygien.mapper.LessonMapper;
import com.example.cyberhygien.repository.LessonRepository;
import com.example.cyberhygien.service.LessonService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LessonServiceExceptionHandlingTest {

    @Mock
    private LessonRepository lessonRepository;

    @InjectMocks
    private LessonService lessonService;

    @Test
    public void testGetLessonByIdLessonNotFound() {

        when(lessonRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> lessonService.getLessonById(1L));
    }

    @Test
    public void testUpdateLessonLessonNotFound() {

        when(lessonRepository.findById(anyLong())).thenReturn(Optional.empty());
        LessonDTO lessonDTO = new LessonDTO();

        assertThrows(EntityNotFoundException.class, () -> lessonService.updateLesson(1L, lessonDTO));
    }

    @Test
    public void testDeleteLessonLessonNotFound() {

        when(lessonRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> lessonService.deleteLesson(1L));
    }
}
