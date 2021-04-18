package com.example.minishop.Controllers;



import org.jinq.jpa.JPAJinqStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import com.example.minishop.Models.*;
import com.example.minishop.Services.*;

// @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("api/users")
public class UsersController {

    @Autowired
    private UowService uow;

    @GetMapping("/getAll/{startIndex}/{pageSize}/{sortBy}/{sortDir}/{email}")
    public ResponseEntity<?> GetAll(
    @PathVariable Long startIndex
    , Long pageSize
    , @PathVariable String sortBy
    , @PathVariable String sortDir
    , @PathVariable String email) {

        JPAJinqStream<User> q = uow.users.jink()
            // .where(e -> email == "*" ? true : e.email.equals(email))
            ;

        // var q2 = uow.users.findBy(query, params)

        Long count = q.count();

        List<User> list = q.toList();

        return ResponseEntity.ok(Map.of("count", count, "list", list));
    }

    @PostMapping("/post")
    public ResponseEntity<?> Post(@RequestBody User model) {
        return ResponseEntity.ok("ok");
    }


    @GetMapping("/test/{id}")
    public ResponseEntity<?> test(@PathVariable("id") Long id) {

        String[] list = { "Volvo", "BMW", "Ford", "Mazda" };

        try {
            // Object o = uow.Practitioners.jink(Practitioner.class).where(e ->
            // e.isActive()).select(e -> e.getFirstname())
            // .toList();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}