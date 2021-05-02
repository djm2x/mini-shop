package com.example.minishop.Repositories;
import javax.annotation.PostConstruct;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UowService {

	@Autowired 
	public UserRepository users;
	
	
}
