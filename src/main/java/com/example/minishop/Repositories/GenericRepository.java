package com.example.minishop.Repositories;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<T extends Serializable, ID > extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    // @Query( value = "SELECT * FROM USERS u WHERE u.status = 1",  nativeQuery = true)
    // List<Book> findByAuthor(String author);
    // List<Book> findByTitle(String title);

    // @Async
    // CompletableFuture<T> findAll();
    // Stream<User> findAllByName(String name);
}

// public class SuperRepository<T extends Serializable> extends SimpleJpaRepository<T, Long> /*implements GenericRepository<T> */{

    
//     public SuperRepository(Class<T> domainClass, EntityManager em) {
//         super(domainClass, em);
//     }

//     // https://reflectoring.io/spring-data-specifications/
//     // https://ultimate.systems/web/blog/generic-controllers-and-services-in-spring-boot-java
// }
