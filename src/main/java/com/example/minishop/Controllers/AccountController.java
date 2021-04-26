package com.example.minishop.Controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import com.example.minishop.Models.*;
import com.example.minishop.Services.JwtService;

@RestController
@RequestMapping("api/users")
public class AccountController extends SuperController<User> {

    private final JwtService jwtService;

    public AccountController(EntityManager em, JwtService jwtTokenUtil) {
        super(User.class, em);

        this.jwtService = jwtTokenUtil;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody UserDto model) {

        Optional<User> user = repository.findOne((r, cq, cb) -> cb.and(
            cb.equal(r.get("email"), model.email), 
            cb.equal(r.get("password"), model.password)
            ));
            ;

            if(user.isPresent() == false){
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }

            if (user.get().password != model.password) {
                return ResponseEntity.ok(Map.of("code", -1));
            }


            Map<String, Object> claims = new HashMap<>();

            claims.put("email", user.get().email);

            String token = jwtService.doGenerateToken(claims, user.get().email);


            return ResponseEntity.ok(Map.of("token", token, "user", user.get()));
    }
}

class UserDto {
    public String email;
    public String password;
}