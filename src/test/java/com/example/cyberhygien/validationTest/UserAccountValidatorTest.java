package com.example.cyberhygien.validationTest;

import com.example.cyberhygien.dto.UserAccountDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import jakarta.validation.ConstraintViolation;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserAccountValidatorTest {

    private LocalValidatorFactoryBean validator;

    @BeforeEach
    public void setUp() {
        validator = new LocalValidatorFactoryBean();
        validator.afterPropertiesSet(); // Initialize the validator
    }

    @Test
    public void testValidUserAccountDTO() {
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        userAccountDTO.setUsername("john_doe");
        userAccountDTO.setEmail("john.doe@example.com");
        userAccountDTO.setPassword("password123");

        Set<ConstraintViolation<UserAccountDTO>> violations = validator.validate(userAccountDTO);
        assertThat(violations).isEmpty();
    }

    @Test
    public void testInvalidUserAccountDTO() {
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        userAccountDTO.setUsername("j"); // Username too short
        userAccountDTO.setEmail("invalid_email"); // Invalid email format
        userAccountDTO.setPassword("12345"); // Password too short

        Set<ConstraintViolation<UserAccountDTO>> violations = validator.validate(userAccountDTO);
        assertThat(violations).hasSize(3); // Expecting violations for username, email, and password
    }
}
