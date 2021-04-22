package com.example.minishop.Services;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<T extends Serializable > extends JpaRepository<T, Long> {

    // @Query( value = "SELECT * FROM USERS u WHERE u.status = 1",  nativeQuery = true)
    // List<Book> findByAuthor(String author);
    // List<Book> findByTitle(String title);
}
