package com.example.minishop.models;
// package com.example.minishop.Models;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.context.event.ApplicationReadyEvent;
// import org.springframework.context.event.ContextRefreshedEvent;
// import org.springframework.context.event.EventListener;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.stereotype.Component;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;
// import java.util.Locale;
// import java.util.logging.Logger;

// import javax.persistence.EntityManager;

// import com.example.minishop.Repositories.GenericRepository;
// import com.github.javafaker.Faker;

// @Component
// public class DatabaseSeeder {

//     // private Logger logger = Logger.getLogger(DatabaseSeeder.class);
//     @Autowired 
//     private GenericRepository<User, Long> users;

//     private final Faker faker = new Faker(new Locale("fr"));


//     public DatabaseSeeder() {
//         // this.users = new SuperRepository<User>(User.class, em);
//         // this.users = new SuperRepository<User>(User.class, em);
//     }

//     // @EventListener
//     // public void seed(ApplicationReadyEvent  event) {
//     //     AddUsers();
//     // }

//     public DatabaseSeeder AddUsers() {
//         List<User> list = new ArrayList<User>();
//         for (int i = 1; i <= 5; i++) {
//             User e = new User();

//             e.id = new Long(i);
//             e.email = faker.internet().emailAddress();
//             e.password = faker.internet().password();
//             e.role = i == 1 ? "admin" : "user";
//             list.add(e);

//             users.save(e);

//         }

//         return this;
//         // users.saveAll(list);
//     }
// }
