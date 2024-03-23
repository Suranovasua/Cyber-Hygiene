package com.example.cyberhygien.controller;

import com.example.cyberhygien.dto.UserAccountDTO;
import com.example.cyberhygien.service.UserAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserAccountControllerTest {

    @Mock
    private UserAccountService userAccountService;

    @InjectMocks
    private UserAccountController userAccountController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userAccountController).build();
    }

    @Test
    void testGetAllUserAccounts() throws Exception {
        when(userAccountService.getAllUserAccounts()).thenReturn(Collections.singletonList(new UserAccountDTO()));

        mockMvc.perform(get("/api/user-accounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetUserAccountById() throws Exception {
        when(userAccountService.getUserAccountById(anyLong())).thenReturn(new UserAccountDTO());

        mockMvc.perform(get("/api/user-accounts/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCreateUserAccount() throws Exception {
        UserAccountDTO userAccountDTO = new UserAccountDTO();

        when(userAccountService.createUserAccount(any(UserAccountDTO.class))).thenReturn(userAccountDTO);

        mockMvc.perform(post("/api/user-accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testUpdateUserAccount() throws Exception {
        UserAccountDTO userAccountDTO = new UserAccountDTO();

        when(userAccountService.updateUserAccount(anyLong(), any(UserAccountDTO.class))).thenReturn(userAccountDTO);

        mockMvc.perform(put("/api/user-accounts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testDeleteUserAccount() throws Exception {
        doNothing().when(userAccountService).deleteUserAccount(anyLong());

        mockMvc.perform(delete("/api/user-accounts/1"))
                .andExpect(status().isNoContent());
    }
}
