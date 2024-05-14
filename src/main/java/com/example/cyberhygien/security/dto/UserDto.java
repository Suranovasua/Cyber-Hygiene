package com.example.cyberhygien.security.dto;



import com.example.cyberhygien.security.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private String username;
    private String email;
    private String phoneNumber;
    private Set<String> roles;

    public UserDto(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.roles = user.getRoles().stream().map(userRole -> userRole.getRolename().name()).collect(Collectors.toSet());
    }
}
