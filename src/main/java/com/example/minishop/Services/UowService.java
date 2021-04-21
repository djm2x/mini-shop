package com.example.minishop.Services;
import javax.annotation.PostConstruct;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.minishop.Models.*;

@Service
public class UowService {

	@Autowired public GenericJpaDao<User> users;
	
	@PostConstruct
	private void UowServiceConstruct() {
		users.setClazz(User.class);
	}
}
