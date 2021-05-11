package com.example.minishop.repositories.old;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public class SuperRepository<T extends Serializable, ID> extends SimpleJpaRepository<T, ID> /*implements GenericRepository<T> */{

    
    public SuperRepository(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
    }

    // https://reflectoring.io/spring-data-specifications/
    // https://ultimate.systems/web/blog/generic-controllers-and-services-in-spring-boot-java
}
