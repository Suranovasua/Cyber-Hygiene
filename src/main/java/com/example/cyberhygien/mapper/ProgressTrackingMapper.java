package com.example.cyberhygien.mapper;

import com.example.cyberhygien.dto.ProgressTrackingDTO;
import com.example.cyberhygien.entity.ProgressTracking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProgressTrackingMapper {

    ProgressTrackingMapper INSTANCE = Mappers.getMapper(ProgressTrackingMapper.class);

    @Mapping(source = "userAccount.id", target = "userAccountId")
    @Mapping(source = "lesson.lessonId", target = "lessonId") // Corrected mapping
    ProgressTrackingDTO progressTrackingToProgressTrackingDTO(ProgressTracking progressTracking);

    @Mapping(source = "userAccountId", target = "userAccount.id")
    @Mapping(source = "lessonId", target = "lesson.lessonId") // Corrected mapping
    ProgressTracking progressTrackingDTOToProgressTracking(ProgressTrackingDTO progressTrackingDTO);
}
