package com.example.cyberhygien.service;

import com.example.cyberhygien.dto.LessonDTO;
import com.example.cyberhygien.entity.Lesson;
import com.example.cyberhygien.mapper.LessonMapper;
import com.example.cyberhygien.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    public List<LessonDTO> getAllLessons() {
        List<Lesson> lessons = lessonRepository.findAll();
        return lessons.stream()
                .map(LessonMapper.INSTANCE::lessonToLessonDTO)
                .collect(Collectors.toList());
    }

    public LessonDTO getLessonById(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new EntityNotFoundException("Lesson not found with id: " + lessonId));
        return LessonMapper.INSTANCE.lessonToLessonDTO(lesson);
    }

    public LessonDTO createLesson(LessonDTO lessonDTO) {
        Lesson lesson = LessonMapper.INSTANCE.lessonDTOToLesson(lessonDTO);
        return LessonMapper.INSTANCE.lessonToLessonDTO(lessonRepository.save(lesson));
    }

    public LessonDTO updateLesson(Long lessonId, LessonDTO lessonDTO) {
        Lesson existingLesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new EntityNotFoundException("Lesson not found with id: " + lessonId));
        existingLesson.setTitle(lessonDTO.getTitle());
        existingLesson.setDescription(lessonDTO.getDescription());
        existingLesson.setTextContent(lessonDTO.getTextContent());
        existingLesson.setImagePath(lessonDTO.getImagePath());
        Lesson updatedLesson = lessonRepository.save(existingLesson);
        return LessonMapper.INSTANCE.lessonToLessonDTO(updatedLesson);
    }

    public void deleteLesson(Long lessonId) {
        if (!lessonRepository.existsById(lessonId)) {
            throw new EntityNotFoundException("Lesson not found with id: " + lessonId);
        }
        lessonRepository.deleteById(lessonId);
    }
}