package com.example.cyberhygien.mapper;

import com.example.cyberhygien.dto.LessonDTO;
import com.example.cyberhygien.entity.Lesson;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LessonMapperTest {

    @Test
    public void testLessonToLessonDTO() {
        Lesson lesson = new Lesson();
        lesson.setLessonId(1L);
        lesson.setTitle("Sample Title");
        lesson.setDescription("Sample Description");
        lesson.setTextContent("Sample Text Content");
        lesson.setImagePath("sample/image/path.jpg");

        LessonDTO lessonDTO = LessonMapper.INSTANCE.lessonToLessonDTO(lesson);


        assertEquals(lesson.getLessonId(), lessonDTO.getLessonId());
        assertEquals(lesson.getTitle(), lessonDTO.getTitle());
        assertEquals(lesson.getDescription(), lessonDTO.getDescription());
        assertEquals(lesson.getTextContent(), lessonDTO.getTextContent());
        assertEquals(lesson.getImagePath(), lessonDTO.getImagePath());
    }

    @Test
    public void testLessonDTOToLesson() {
        // Given
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setLessonId(1L);
        lessonDTO.setTitle("Sample Title");
        lessonDTO.setDescription("Sample Description");
        lessonDTO.setTextContent("Sample Text Content");
        lessonDTO.setImagePath("sample/image/path.jpg");

        // When
        Lesson lesson = LessonMapper.INSTANCE.lessonDTOToLesson(lessonDTO);

        // Then
        assertEquals(lessonDTO.getLessonId(), lesson.getLessonId());
        assertEquals(lessonDTO.getTitle(), lesson.getTitle());
        assertEquals(lessonDTO.getDescription(), lesson.getDescription());
        assertEquals(lessonDTO.getTextContent(), lesson.getTextContent());
        assertEquals(lessonDTO.getImagePath(), lesson.getImagePath());
    }
}
