package com.example.minishop.Controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import com.example.minishop.Models.*;

@RestController
@RequestMapping("api/users")
public class UsersController extends SuperController<User> {

    public UsersController(EntityManager em) {
        super(User.class, em);
    }

    @GetMapping("/getAll/{startIndex}/{pageSize}/{sortBy}/{sortDir}/{email}")
    @Override
    public ResponseEntity<?> GetAll(@PathVariable int startIndex, @PathVariable int pageSize,
            @PathVariable String sortBy, @PathVariable String sortDir, @PathVariable String email) {

        Page<User> query = repository
            .findAll((r, q, cb) -> email.equals("*") ? cb.and() : cb.like(r.get("email"), "%"+email+"%"), PageRequest.of(startIndex, pageSize))
            ;

        List<User> list = query.getContent();

        Long count = query.getTotalElements();

        return ResponseEntity.ok(Map.of("count", count, "list", list));
    }
}