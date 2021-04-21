package com.example.minishop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import com.example.minishop.Models.*;
import com.example.minishop.Services.*;

@RestController
@RequestMapping("api/users")
public class UsersController extends GenericController<User> {

    // @Autowired
    private UowService uow;

    public UsersController(UowService uow, GenericRepository<User> repository) {
        super(repository);
        this.uow = uow;
    }

    @GetMapping("/getAll/{startIndex}/{pageSize}/{sortBy}/{sortDir}/{email}")
    public ResponseEntity<?> GetAll(@PathVariable int startIndex, @PathVariable int pageSize,
            @PathVariable String sortBy, @PathVariable String sortDir, @PathVariable String email) {

        // Query query = uow.users.createQuery("From User");
        // Query query = uow.users.createQuery("From User");
        Object query = uow.users.createQuery("From User").setMaxResults(pageSize).getResultList();

        // var q2 = uow.users.findBy(query, params)

        int count = 0;//(int) query.getSingleResult();

        // Object list = query.setFirstResult(startIndex).setMaxResults(pageSize).getResultList();

        return ResponseEntity.ok(Map.of("count", count, "list", query));
    }

    // @PostMapping("/post")
    // public ResponseEntity<?> Post(@RequestBody User model) {
    //     return ResponseEntity.ok(uow.users.save(model));
    // }

    // @GetMapping("/test/{id}")
    // public ResponseEntity<?> test(@PathVariable("id") Long id) {

    //     String[] list = { "Volvo", "BMW", "Ford", "Mazda" };

    //     try {
    //         // Object o = uow.Practitioners.jink(Practitioner.class).where(e ->
    //         // e.isActive()).select(e -> e.getFirstname())
    //         // .toList();

    //         return ResponseEntity.ok(uow.users.findAll());
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    //     }
    // }
}