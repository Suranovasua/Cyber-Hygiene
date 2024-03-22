package com.example.cyberhygien.mapper;

import com.example.cyberhygien.dto.LessonDTO;
import com.example.cyberhygien.entity.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LessonMapper {

    LessonMapper INSTANCE = Mappers.getMapper(LessonMapper.class);

    LessonDTO lessonToLessonDTO(Lesson lesson);

    @Mapping(target = "lessonId", ignore = true) // Ignore lessonId when mapping from DTO to entity
    Lesson lessonDTOToLesson(LessonDTO lessonDTO);
}
