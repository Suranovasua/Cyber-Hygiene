package com.example.cyberhygien.repository;

import com.example.cyberhygien.entity.UserAccount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class UserAccountRepositoryTest {

    @Mock
    private UserAccountRepository userAccountRepository;

    @Test
    void testFindAll() {

        UserAccount userAccount1 = new UserAccount();
        userAccount1.setUserId(1L);
        userAccount1.setUsername("user1");

        UserAccount userAccount2 = new UserAccount();
        userAccount2.setUserId(2L);
        userAccount2.setUsername("user2");

        when(userAccountRepository.findAll()).thenReturn(List.of(userAccount1, userAccount2));


        List<UserAccount> userAccounts = userAccountRepository.findAll();

        assertEquals(2, userAccounts.size());
        assertEquals("user1", userAccounts.get(0).getUsername());
        assertEquals("user2", userAccounts.get(1).getUsername());
        verify(userAccountRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {

        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(1L);
        userAccount.setUsername("user1");

        when(userAccountRepository.findById(1L)).thenReturn(Optional.of(userAccount));


        Optional<UserAccount> foundUserAccount = userAccountRepository.findById(1L);


        assertEquals("user1", foundUserAccount.get().getUsername());
        verify(userAccountRepository, times(1)).findById(1L);
    }


}
