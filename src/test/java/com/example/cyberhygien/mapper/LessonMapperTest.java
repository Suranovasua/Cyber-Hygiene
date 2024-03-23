package com.example.cyberhygien.mapper;

import com.example.cyberhygien.dto.LessonDTO;
import com.example.cyberhygien.entity.Lesson;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LessonMapperTest {

    private final LessonMapper mapper = Mappers.getMapper(LessonMapper.class);

    @Test
    public void testLessonToLessonDTO() {

        Lesson lesson = new Lesson();
        lesson.setLessonId(1L);
        lesson.setTitle("Sample Title");
        lesson.setDescription("Sample Description");
        lesson.setTextContent("Sample Text Content");
        lesson.setImagePath("sample/image/path.jpg");


        LessonDTO lessonDTO = mapper.lessonToLessonDTO(lesson);


        assertEquals(lesson.getLessonId(), lessonDTO.getLessonId());
        assertEquals(lesson.getTitle(), lessonDTO.getTitle());
        assertEquals(lesson.getDescription(), lessonDTO.getDescription());
        assertEquals(lesson.getTextContent(), lessonDTO.getTextContent());
        assertEquals(lesson.getImagePath(), lessonDTO.getImagePath());
    }

    @Test
    public void testLessonDTOToLesson() {

        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setTitle("Sample Title");
        lessonDTO.setDescription("Sample Description");
        lessonDTO.setTextContent("Sample Text Content");
        lessonDTO.setImagePath("sample/image/path.jpg");


        Lesson lesson = mapper.lessonDTOToLesson(lessonDTO);


        assertEquals(lessonDTO.getTitle(), lesson.getTitle());
        assertEquals(lessonDTO.getDescription(), lesson.getDescription());
        assertEquals(lessonDTO.getTextContent(), lesson.getTextContent());
        assertEquals(lessonDTO.getImagePath(), lesson.getImagePath());

        assertEquals(null, lesson.getLessonId());
    }
}
