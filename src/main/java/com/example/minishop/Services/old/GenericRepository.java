package com.example.minishop.Services.old;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.scheduling.annotation.Async;

@NoRepositoryBean
public interface GenericRepository<T extends Serializable > extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

    // @Query( value = "SELECT * FROM USERS u WHERE u.status = 1",  nativeQuery = true)
    // List<Book> findByAuthor(String author);
    // List<Book> findByTitle(String title);

    // @Async
    // CompletableFuture<T> findAll();
    // Stream<User> findAllByName(String name);
}
