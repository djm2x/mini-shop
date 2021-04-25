package com.example.minishop.Services;

import java.io.Serializable;

import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class SuperRepository<T extends Serializable> extends SimpleJpaRepository<T, Long> /*implements GenericRepository<T> */{

    
    public SuperRepository(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
    }

    // https://reflectoring.io/spring-data-specifications/
}
