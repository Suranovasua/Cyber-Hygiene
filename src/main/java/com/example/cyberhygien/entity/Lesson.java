package com.example.cyberhygien.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonId;

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must be less than or equal to 255 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 1000, message = "Description must be less than or equal to 1000 characters")
    private String description;

    @Lob
    private String textContent;

    @NotBlank(message = "Image path is required")
    @Size(max = 255, message = "Image path must be less than or equal to 255 characters")
    private String imagePath;

}
