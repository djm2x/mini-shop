package com.example.minishop.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.example.minishop.configuration.security.JwtTokenUtil;
import com.example.minishop.models.User;
import com.example.minishop.repositories.UowService;

@RestController
@RequestMapping("api/accounts")
public class AccountController extends SuperController<User, Long> {

    private final JwtTokenUtil jwtTokenUtil;
    private final UowService uow;

    public AccountController(UowService uow, JwtTokenUtil jwtTokenUtil) {
        super(uow.users);

        this.uow = uow;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto model) {

        Optional<User> op = uow.users.findByEmail(model.email);
            ;

        // Optional<User> op = repository.findOne((r, cq, cb) -> cb.and(
        //     cb.equal(r.get("email"), model.email)//, 
        //     // cb.equal(r.get("password"), model.password)
        //     ));
        //     ;

            if(op.isPresent() == false){
                // return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
                return ResponseEntity.ok(Map.of("code", -1, "message", "Email incorrect"));
            }

            User user = op.get();

            if (user.password.equals(model.password) == false) {
                return ResponseEntity.ok(Map.of("code", -1, "message", "Password incorrect"));
            }


            Map<String, Object> claims = new HashMap<>();

            claims.put("email", user.email);
            claims.put("role", user.role);
            claims.put("id", user.id);

            String token = jwtTokenUtil.doGenerateToken(claims, user.email);


            return ResponseEntity.ok(Map.of("token", token, "user", user, "message", "Connexion reussite"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User model) {

        Optional<User> userExiste = repository.findOne((r, cq, cb) -> cb.and(
            cb.equal(r.get("email"), model.email)
            ));
            ;

            if(userExiste.isPresent() == true){
                return ResponseEntity.ok(Map.of("code", -1, "message", "Email already takking"));
            }

            User user = repository.save(model);


            Map<String, Object> claims = new HashMap<>();

            claims.put("email", user.email);

            String token = jwtTokenUtil.doGenerateToken(claims, user.email);


            return ResponseEntity.ok(Map.of("token", token, "user", user));
    }
}

// @Data
class UserDto {
    // @NotNull @Email
    public String email;
    // @NotNull
    public String password;
}