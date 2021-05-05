package com.example.minishop.Controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.example.minishop.Models.*;
import com.example.minishop.Repositories.UowService;
import com.example.minishop.Services.JwtService;

@RestController
@RequestMapping("api/accounts")
public class AccountController extends SuperController<User, Long> {

    private final JwtService jwtService;

    public AccountController(UowService uow, JwtService jwtTokenUtil) {
        super(uow.users);

        this.jwtService = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto model) {

        Optional<User> op = repository.findOne((r, cq, cb) -> cb.and(
            cb.equal(r.get("email"), model.email), 
            cb.equal(r.get("password"), model.password)
            ));
            ;

            if(op.isPresent() == false){
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }

            User user = op.get();

            if (user.password.equals(model.password) == false) {
                return ResponseEntity.ok(Map.of("code", -1, "message", "Password incorrect"));
            }


            Map<String, Object> claims = new HashMap<>();

            claims.put("email", user.email);

            String token = jwtService.doGenerateToken(claims, user.email);


            return ResponseEntity.ok(Map.of("token", token, "user", user));
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

            String token = jwtService.doGenerateToken(claims, user.email);


            return ResponseEntity.ok(Map.of("token", token, "user", user));
    }
}

class UserDto {
    public String email;
    public String password;
}