package com.example.cyberhygien.repository;

import com.example.cyberhygien.entity.Lesson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class LessonRepositoryTest {

    @Mock
    private LessonRepository lessonRepository;

    @Test
    void testFindAll() {
        // Given
        Lesson lesson1 = new Lesson();
        lesson1.setLessonId(1L);
        lesson1.setTitle("Lesson 1");

        Lesson lesson2 = new Lesson();
        lesson2.setLessonId(2L);
        lesson2.setTitle("Lesson 2");

        when(lessonRepository.findAll()).thenReturn(List.of(lesson1, lesson2));

        List<Lesson> lessons = lessonRepository.findAll();


        assertEquals(2, lessons.size());
        assertEquals("Lesson 1", lessons.get(0).getTitle());
        assertEquals("Lesson 2", lessons.get(1).getTitle());
        verify(lessonRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Lesson lesson = new Lesson();
        lesson.setLessonId(1L);
        lesson.setTitle("Lesson 1");

        when(lessonRepository.findById(1L)).thenReturn(Optional.of(lesson));


        Optional<Lesson> foundLesson = lessonRepository.findById(1L);


        assertEquals("Lesson 1", foundLesson.get().getTitle());
        verify(lessonRepository, times(1)).findById(1L);
    }


}
