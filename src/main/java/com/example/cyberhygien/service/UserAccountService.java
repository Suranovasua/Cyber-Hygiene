package com.example.cyberhygien.service;

import com.example.cyberhygien.dto.UserAccountDTO;
import com.example.cyberhygien.entity.UserAccount;
import com.example.cyberhygien.mapper.UserAccountMapper;
import com.example.cyberhygien.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    public List<UserAccountDTO> getAllUserAccounts() {
        List<UserAccount> userAccounts = userAccountRepository.findAll();
        return userAccounts.stream()
                .map(UserAccountMapper.INSTANCE::entityToDTO)
                .collect(Collectors.toList());
    }

    public UserAccountDTO getUserAccountById(Long userId) {
        UserAccount userAccount = userAccountRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("UserAccount not found with id: " + userId));
        return UserAccountMapper.INSTANCE.entityToDTO(userAccount);
    }

    public UserAccountDTO createUserAccount(UserAccountDTO userAccountDTO) {
        UserAccount userAccount = UserAccountMapper.INSTANCE.dtoToEntity(userAccountDTO);
        return UserAccountMapper.INSTANCE.entityToDTO(userAccountRepository.save(userAccount));
    }

    public UserAccountDTO updateUserAccount(Long userId, UserAccountDTO userAccountDTO) {
        UserAccount existingUserAccount = userAccountRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("UserAccount not found with id: " + userId));
        existingUserAccount.setUsername(userAccountDTO.getUsername());
        existingUserAccount.setEmail(userAccountDTO.getEmail());
        existingUserAccount.setPassword(userAccountDTO.getPassword());
        existingUserAccount.setRole(userAccountDTO.getRole());
        UserAccount updatedUserAccount = userAccountRepository.save(existingUserAccount);
        return UserAccountMapper.INSTANCE.entityToDTO(updatedUserAccount);
    }

    public void deleteUserAccount(Long userId) {
        if (!userAccountRepository.existsById(userId)) {
            throw new EntityNotFoundException("UserAccount not found with id: " + userId);
        }
        userAccountRepository.deleteById(userId);
    }
}
