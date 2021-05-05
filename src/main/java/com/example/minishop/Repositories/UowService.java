package com.example.minishop.Repositories;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.example.minishop.Models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UowService {

	// @Autowired 
	// public GenericRepository<User, Long> users;

	// public SuperRepository<User, Long> users1;
	// public UserRepository users;
	// // @Autowired 
	// // public GenericRepository<User, Long> users;

	// @PersistenceContext
	// EntityManager entityManager;

	// public UowService(EntityManager entityManage) {
	// 	// this.users = new UserRepository(entityManage);
	// 	users = new UserRepository(entityManage);
	// }

	@Autowired
    public GenericRepository<User, Long> users;

	
}
