package com.example.cyberhygien.validationTest;

import com.example.cyberhygien.dto.ProgressTrackingDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import jakarta.validation.ConstraintViolation;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProgressTrackingValidatorTest {

    private LocalValidatorFactoryBean validator;

    @BeforeEach
    public void setUp() {
        validator = new LocalValidatorFactoryBean();
        validator.afterPropertiesSet(); // Initialize the validator
    }

    @Test
    public void testValidProgressTrackingDTO() {
        ProgressTrackingDTO progressTrackingDTO = ProgressTrackingDTO.builder()
                .userAccountId(1L)
                .lessonId(2L)
                .completionStatus(true)
                .completionDate(LocalDateTime.now())
                .build();

        Set<ConstraintViolation<ProgressTrackingDTO>> violations = validator.validate(progressTrackingDTO);
        assertThat(violations).isEmpty();
    }

    @Test
    public void testInvalidProgressTrackingDTO() {
        ProgressTrackingDTO progressTrackingDTO = ProgressTrackingDTO.builder()
                .userAccountId(null) // User account ID is null
                .lessonId(null) // Lesson ID is null
                .completionStatus(null) // Completion status is null
                .completionDate(null) // Completion date is null
                .build();

        Set<ConstraintViolation<ProgressTrackingDTO>> violations = validator.validate(progressTrackingDTO);
        assertThat(violations).hasSize(4);


        assertThat(violations.stream().map(ConstraintViolation::getMessage))
                .contains("не должно равняться null",
                        "не должно равняться null",
                        "Completion status must not be null",
                        "не должно равняться null");
    }

}
