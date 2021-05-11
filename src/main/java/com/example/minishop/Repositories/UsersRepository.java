package com.example.minishop.repositories;

import java.util.Optional;

import com.example.minishop.models.User;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersRepository extends GenericRepository<User, Long> {
    public Optional<User> findByUsername(String username);
    public Optional<User> findByEmail(String email);
}
