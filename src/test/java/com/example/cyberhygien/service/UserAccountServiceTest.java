package com.example.cyberhygien.service;

import com.example.cyberhygien.dto.UserAccountDTO;
import com.example.cyberhygien.entity.UserAccount;
import com.example.cyberhygien.mapper.UserAccountMapper;
import com.example.cyberhygien.repository.UserAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserAccountServiceTest {

    @Mock
    private UserAccountRepository userAccountRepository;

    @InjectMocks
    private UserAccountService userAccountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUserAccounts_Success() {
        List<UserAccount> userAccounts = new ArrayList<>();
        userAccounts.add(new UserAccount(1L, "username1", "email1@example.com", "password1", "ROLE_USER"));
        userAccounts.add(new UserAccount(2L, "username2", "email2@example.com", "password2", "ROLE_ADMIN"));

        when(userAccountRepository.findAll()).thenReturn(userAccounts);

        List<UserAccountDTO> userAccountDTOs = userAccountService.getAllUserAccounts();

        assertEquals(userAccounts.size(), userAccountDTOs.size());
        for (int i = 0; i < userAccounts.size(); i++) {
            UserAccountDTO expectedDTO = UserAccountMapper.INSTANCE.entityToDTO(userAccounts.get(i));
            UserAccountDTO actualDTO = userAccountDTOs.get(i);
            assertEquals(expectedDTO, actualDTO);
        }
    }

    @Test
    public void testGetUserAccountById_Success() {
        UserAccount userAccount = new UserAccount(1L, "username", "email@example.com", "password", "ROLE_USER");
        when(userAccountRepository.findById(1L)).thenReturn(Optional.of(userAccount));

        UserAccountDTO userAccountDTO = userAccountService.getUserAccountById(1L);

        UserAccountDTO expectedDTO = UserAccountMapper.INSTANCE.entityToDTO(userAccount);
        assertEquals(expectedDTO, userAccountDTO);
    }

    @Test
    public void testCreateUserAccount_Success() {
        UserAccountDTO userAccountDTO = new UserAccountDTO(null, "username", "email@example.com", "password", "ROLE_USER");
        UserAccount userAccount = UserAccountMapper.INSTANCE.dtoToEntity(userAccountDTO);

        when(userAccountRepository.save(any(UserAccount.class))).thenReturn(userAccount);

        UserAccountDTO createdUserAccountDTO = userAccountService.createUserAccount(userAccountDTO);

        assertEquals(userAccountDTO, createdUserAccountDTO);
    }

    @Test
    public void testUpdateUserAccount_Success() {
        UserAccount existingUserAccount = new UserAccount(1L, "username", "email@example.com", "password", "ROLE_USER");
        UserAccountDTO userAccountDTO = new UserAccountDTO(1L, "updated_username", "updated_email@example.com", "updated_password", "ROLE_ADMIN");

        when(userAccountRepository.findById(1L)).thenReturn(Optional.of(existingUserAccount));
        when(userAccountRepository.save(any(UserAccount.class))).thenReturn(existingUserAccount);

        UserAccountDTO updatedUserAccountDTO = userAccountService.updateUserAccount(1L, userAccountDTO);

        assertEquals(userAccountDTO, updatedUserAccountDTO);
    }

    @Test
    public void testDeleteUserAccount_Success() {
        when(userAccountRepository.existsById(1L)).thenReturn(true);

        userAccountService.deleteUserAccount(1L);

        verify(userAccountRepository, times(1)).deleteById(1L);
    }
}
