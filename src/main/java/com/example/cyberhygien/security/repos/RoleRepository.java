package com.example.cyberhygien.security.repos;


import com.example.cyberhygien.security.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<UserRole, UserRole.Name>,
        JpaRepository<UserRole, UserRole.Name> {

}
