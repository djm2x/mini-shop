package com.example.minishop.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.example.minishop.repositories.UowService;
import com.example.minishop.repositories.UsersRepository;
import com.github.javafaker.Faker;

@Component
public class DatabaseSeeder  /*implements ApplicationRunner*/ {

    // private Logger logger = Logger.getLogger(DatabaseSeeder.class);
    @Autowired 
    private UowService uow;

    private final Faker faker = new Faker(new Locale("fr"));


    // public DatabaseSeeder(UowService uow) {
    //     this.uow = uow;
    //     // this.users = new SuperRepository<User>(User.class, em);
    //     // this.users = new SuperRepository<User>(User.class, em);
    // }

    // public void run(ApplicationArguments args) {
    //     AddUsers();
    // }

    @EventListener
    public void seed(ApplicationReadyEvent  event) {
        AddUsers();
    }

    public DatabaseSeeder AddUsers() {
        List<User> list = new ArrayList<User>();
        for (Long i = 1L; i <= 5; i++) {
            User e = new User();

            e.id = i;
            e.email = i == 1 ? "sa@angular.io" : faker.internet().emailAddress();
            e.password = "123";//faker.internet().password();
            e.role = i == 1 ? "admin" : "user";
            e.username = faker.name().username();
            e.isActive = faker.bool().bool();
            list.add(e);

            uow.users.save(e);
        }

        // uow.users.saveAll(list);
        return this;
    }
}
