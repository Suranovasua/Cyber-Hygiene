package com.example.cyberhygien.mapper;

import com.example.cyberhygien.dto.UserAccountDTO;
import com.example.cyberhygien.entity.UserAccount;
import com.example.cyberhygien.mapper.UserAccountMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserAccountMapperTest {

    private final UserAccountMapper mapper = Mappers.getMapper(UserAccountMapper.class);

    @Test
    public void testEntityToDTO() {

        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(1L);
        userAccount.setUsername("testuser");
        userAccount.setEmail("test@example.com");
        userAccount.setPassword("password");
        userAccount.setRole("USER");


        UserAccountDTO userAccountDTO = mapper.entityToDTO(userAccount);


        assertEquals(userAccount.getUserId(), userAccountDTO.getUserId());
        assertEquals(userAccount.getUsername(), userAccountDTO.getUsername());
        assertEquals(userAccount.getEmail(), userAccountDTO.getEmail());
        assertEquals(userAccount.getPassword(), userAccountDTO.getPassword());
        assertEquals(userAccount.getRole(), userAccountDTO.getRole());
    }

    @Test
    public void testDtoToEntity() {
        // Given
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        userAccountDTO.setUsername("testuser");
        userAccountDTO.setEmail("test@example.com");
        userAccountDTO.setPassword("password");
        userAccountDTO.setRole("USER");

        UserAccount userAccount = mapper.dtoToEntity(userAccountDTO);


        assertEquals(userAccountDTO.getUsername(), userAccount.getUsername());
        assertEquals(userAccountDTO.getEmail(), userAccount.getEmail());
        assertEquals(userAccountDTO.getPassword(), userAccount.getPassword());
        assertEquals(userAccountDTO.getRole(), userAccount.getRole());
        assertEquals(null, userAccount.getUserId());
    }
}
