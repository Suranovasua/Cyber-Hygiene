package com.example.cyberhygien.validationTest;

import com.example.cyberhygien.dto.LessonDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import jakarta.validation.ConstraintViolation;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LessonValidatorTest {

    private LocalValidatorFactoryBean validator;

    @BeforeEach
    public void setUp() {
        validator = new LocalValidatorFactoryBean();
        validator.afterPropertiesSet(); // Initialize the validator
    }

    @Test
    public void testValidLessonDTO() {
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setTitle("Introduction to Java");
        lessonDTO.setDescription("This is an introduction to Java programming language.");
        lessonDTO.setImagePath("/images/java.jpg");

        Set<ConstraintViolation<LessonDTO>> violations = validator.validate(lessonDTO);
        assertThat(violations).isEmpty();
    }

    @Test
    public void testInvalidLessonDTO() {
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setTitle(""); // Title is empty
        lessonDTO.setDescription("This is an introduction to Java programming language.");
        lessonDTO.setImagePath("/images/java.jpg");

        Set<ConstraintViolation<LessonDTO>> violations = validator.validate(lessonDTO);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Title is required");
    }
}
