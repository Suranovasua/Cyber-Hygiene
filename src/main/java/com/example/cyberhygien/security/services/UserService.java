package com.example.cyberhygien.security.services;//package com.example.cyberhygien.security.services;

import com.example.cyberhygien.security.dto.UserReqDto;
import com.example.cyberhygien.security.entities.User;
import com.example.cyberhygien.security.entities.UserRole;
import com.example.cyberhygien.security.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    public void addUser(UserReqDto userReqDto) {
        // Convert UserReqDto to User entity
        User user = new User();
        user.setUsername(userReqDto.getUsername());
        user.setEmail(userReqDto.getEmail());
        user.setPhoneNumber(userReqDto.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(userReqDto.getPassword()));


        // Save user to the database
        userRepository.save(user);
    }
}

