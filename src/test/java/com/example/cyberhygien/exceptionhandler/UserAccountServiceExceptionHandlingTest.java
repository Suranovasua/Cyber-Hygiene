package com.example.cyberhygien.exceptionhandler;

import com.example.cyberhygien.dto.UserAccountDTO;
import com.example.cyberhygien.entity.UserAccount;
import com.example.cyberhygien.mapper.UserAccountMapper;
import com.example.cyberhygien.repository.UserAccountRepository;
import com.example.cyberhygien.service.UserAccountService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserAccountServiceExceptionHandlingTest {

    @Mock
    private UserAccountRepository userAccountRepository;

    @InjectMocks
    private UserAccountService userAccountService;

    @Test
    public void testGetUserAccountByIdUserAccountNotFound() {
        // Arrange
        when(userAccountRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> userAccountService.getUserAccountById(1L));
    }

    @Test
    public void testUpdateUserAccountUserAccountNotFound() {
        // Arrange
        when(userAccountRepository.findById(anyLong())).thenReturn(Optional.empty());
        UserAccountDTO userAccountDTO = new UserAccountDTO(); // create a dummy DTO for testing

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> userAccountService.updateUserAccount(1L, userAccountDTO));
    }

    @Test
    public void testDeleteUserAccountUserAccountNotFound() {
        // Arrange
        when(userAccountRepository.existsById(anyLong())).thenReturn(false);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> userAccountService.deleteUserAccount(1L));
    }
}
