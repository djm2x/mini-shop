package com.example.minishop.Repositories;

import com.example.minishop.Models.User;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class UserRepository extends GenericRepositoryImp<User, Long> { }
