// package com.example.minishop.Controllers;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Locale;
// import java.util.Map;

// import com.example.minishop.Models.User;
// import com.example.minishop.Repositories.GenericRepository;
// import com.github.javafaker.Faker;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;


// @RestController
// @RequestMapping("api/seeds")
// public class SeedController {

//     @Autowired 
//     private GenericRepository<User, Long> users;
    
//     private final Faker faker = new Faker(new Locale("fr"));
    
//     public SeedController() { }
    
//     @GetMapping("/apply")
//     public ResponseEntity<?>  apply() {

//         try {
//             AddUsers()
//             ;
//         } catch (Exception e) {
//             return ResponseEntity.ok(Map.of("msg", e.getMessage(), "StackTrace", e.getStackTrace()));
//         }

//         return ResponseEntity.ok(Map.of("msg", "ok"));
//     }

//     private SeedController AddUsers() {
//         List<User> list = new ArrayList<User>();
//         for (int i = 1; i <= 5; i++) {
//             User e = new User();

//             e.id = new Long(i);
//             e.email = faker.internet().emailAddress();
//             e.password = faker.internet().password();
//             e.role = i == 1 ? "admin" : "user";
//             list.add(e);

            
//         }
        
//         users.saveAll(list);
//         return this;
//     }
// }
