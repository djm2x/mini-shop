package com.example.minishop.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.example.minishop.services.*;

@RestController
@RequestMapping("api/accounts")
public class AccountController extends SuperController<User, Long> {

    protected final JwtTokenUtil jwtTokenUtil;
    protected final UowService uow;
    protected final BCryptPasswordEncoder pwEncoder;
    protected final MailService mailService;
    protected final HtmlService htmlService;

    public AccountController(UowService uow, JwtTokenUtil jwtTokenUtil, BCryptPasswordEncoder bCryptPasswordEncoder,
            MailService mailService, HtmlService htmlService) {
        super(uow.users);

        this.uow = uow;
        this.jwtTokenUtil = jwtTokenUtil;
        this.pwEncoder = bCryptPasswordEncoder;
        this.mailService = mailService;
        this.htmlService = htmlService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto model) {

        Optional<User> op = uow.users.findByEmail(model.email);

        // Optional<User> op = repository.findOne((r, cq, cb) -> cb.and(
        // cb.equal(r.get("email"), model.email)//,
        // // cb.equal(r.get("password"), model.password)
        // ));
        // ;

        if (op.isPresent() == false) {
            // return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            return ResponseEntity.ok(Map.of("code", -1, "message", "Email incorrect"));
        }

        User user = op.get();

        // String b = user.password;
        // String b0 = model.password;
        // String b3 = pwEncoder.encode(model.password);
        // boolean b2 = pwEncoder.matches(user.password, model.password);


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

        Optional<User> userExiste = uow.users.findByEmail(model.email);

        if (userExiste.isPresent() == true) {
            return ResponseEntity.ok(Map.of("code", -1, "message", "Email already takking"));
        }

        model.password = pwEncoder.encode(model.password);

        User user = repository.save(model);

        String html = htmlService.prepareRegisterHtml("mini-shop.com", user.email);

        boolean b = mailService.sendEmail(user.email, "register in mini-shop", html);

        Map<String, Object> claims = Map.of("email", user.email, "role", user.role, "id", user.id);

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