package com.example.cyberhygien.service;

import com.example.cyberhygien.dto.LessonDTO;
import com.example.cyberhygien.entity.Lesson;
import com.example.cyberhygien.mapper.LessonMapper;
import com.example.cyberhygien.repository.LessonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LessonServiceTest {

    @Mock
    private LessonRepository lessonRepository;

    @InjectMocks
    private LessonService lessonService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllLessons_Success() {
        List<Lesson> lessons = new ArrayList<>();
        lessons.add(new Lesson());
        when(lessonRepository.findAll()).thenReturn(lessons);

        List<LessonDTO> lessonDTOs = lessonService.getAllLessons();

        assertEquals(1, lessonDTOs.size());
    }

    @Test
    public void testGetLessonById_Success() {
        Long lessonId = 1L;
        Lesson lesson = new Lesson();
        lesson.setLessonId(lessonId);
        when(lessonRepository.findById(lessonId)).thenReturn(Optional.of(lesson));

        LessonDTO lessonDTO = lessonService.getLessonById(lessonId);

        assertEquals(lessonId, lessonDTO.getLessonId());
    }

    @Test
    public void testCreateLesson_Success() {
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setTitle("Sample Title");
        lessonDTO.setDescription("Sample Description");
        lessonDTO.setImagePath("dfasf");
        lessonDTO.setTextContent("dfjasjf");

        Lesson lesson = LessonMapper.INSTANCE.lessonDTOToLesson(lessonDTO);
        when(lessonRepository.save(any(Lesson.class))).thenReturn(lesson);


        LessonDTO createdLessonDTO = lessonService.createLesson(lessonDTO);

        assertEquals(lessonDTO.getTitle(), createdLessonDTO.getTitle());
        assertEquals(lessonDTO.getDescription(), createdLessonDTO.getDescription());
        assertEquals(lessonDTO.getImagePath(), createdLessonDTO.getImagePath());
        assertEquals(lessonDTO.getTextContent(), createdLessonDTO.getTextContent());
    }



    @Test
    public void testUpdateLesson_Success() {
        Long lessonId = 1L;
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setLessonId(lessonId);
        Lesson existingLesson = new Lesson();
        existingLesson.setLessonId(lessonId);
        when(lessonRepository.findById(lessonId)).thenReturn(Optional.of(existingLesson));
        when(lessonRepository.save(any(Lesson.class))).thenReturn(existingLesson);

        LessonDTO updatedLessonDTO = lessonService.updateLesson(lessonId, lessonDTO);

        assertEquals(lessonDTO, updatedLessonDTO);
    }

    @Test
    public void testDeleteLesson_Success() {
        Long lessonId = 1L;
        when(lessonRepository.existsById(lessonId)).thenReturn(true);

        lessonService.deleteLesson(lessonId);

        verify(lessonRepository, times(1)).deleteById(lessonId);
    }

}
