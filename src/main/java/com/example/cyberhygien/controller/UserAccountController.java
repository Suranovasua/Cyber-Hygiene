package com.example.cyberhygien.controller;

import com.example.cyberhygien.dto.UserAccountDTO;
import com.example.cyberhygien.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user-accounts")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @GetMapping
    public ResponseEntity<List<UserAccountDTO>> getAllUserAccounts() {
        List<UserAccountDTO> userAccounts = userAccountService.getAllUserAccounts();
        return ResponseEntity.ok(userAccounts);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserAccountDTO> getUserAccountById(@PathVariable Long userId) {
        UserAccountDTO userAccountDTO = userAccountService.getUserAccountById(userId);
        return ResponseEntity.ok(userAccountDTO);
    }

    @PostMapping
    public ResponseEntity<?> createUserAccount(@Valid @RequestBody UserAccountDTO userAccountDTO) {
        UserAccountDTO createdUserAccount = userAccountService.createUserAccount(userAccountDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserAccount);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserAccountDTO> updateUserAccount(@PathVariable Long userId,@Valid @RequestBody UserAccountDTO userAccountDTO) {
        UserAccountDTO updatedUserAccount = userAccountService.updateUserAccount(userId, userAccountDTO);
        return ResponseEntity.ok(updatedUserAccount);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserAccount(@PathVariable Long userId) {
        userAccountService.deleteUserAccount(userId);
        return ResponseEntity.noContent().build();
    }
}
