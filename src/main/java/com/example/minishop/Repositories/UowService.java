package com.example.minishop.repositories;
import javax.transaction.Transactional;


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
    public UsersRepository users;

	
}
