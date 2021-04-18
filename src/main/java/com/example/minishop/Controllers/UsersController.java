package com.example.minishop.Controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.minishop.Models.*;
import com.example.minishop.Services.*;

@RestController
@RequestMapping("api/users")
public class UsersController {

    @Autowired
    private UowService uow;

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