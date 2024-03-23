package com.example.cyberhygien.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonDTO {

    private Long lessonId;

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must be less than or equal to 255 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 1000, message = "Description must be less than or equal to 1000 characters")
    private String description;

    private String textContent;

    @NotBlank(message = "Image path is required")
    @Size(max = 255, message = "Image path must be less than or equal to 255 characters")
    private String imagePath;

}
