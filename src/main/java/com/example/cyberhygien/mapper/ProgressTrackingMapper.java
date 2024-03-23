package com.example.cyberhygien.mapper;

import com.example.cyberhygien.dto.ProgressTrackingDTO;
import com.example.cyberhygien.entity.ProgressTracking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProgressTrackingMapper {

    ProgressTrackingMapper INSTANCE = Mappers.getMapper(ProgressTrackingMapper.class);

    @Mapping(source = "userAccount.userId", target = "userAccountId")
    @Mapping(source = "lesson.lessonId", target = "lessonId")
    ProgressTrackingDTO progressTrackingToProgressTrackingDTO(ProgressTracking progressTracking);

    @Mapping(source = "userAccountId", target = "userAccount.userId")
    @Mapping(source = "lessonId", target = "lesson.lessonId")
    ProgressTracking progressTrackingDTOToProgressTracking(ProgressTrackingDTO progressTrackingDTO);
}
