
// import java.util.Collections;
// import java.util.HashMap;
// import java.util.Map;

// import com.fasterxml.jackson.databind.ObjectMapper;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RestController;

// import lombok.var;

// // @CrossOrigin
// @RestController
// @RequestMapping("api/users")
// public class UsersController {

//     @Autowired
//     private UowService uow;

//     @PostMapping("/login")
//     public ResponseEntity<?> login(@RequestBody JwtRequest model) {

//         // Users user = new Users();//uow.findBy("from Users where email = ?0", model.getEmail()).get(0);
//         String email = model.getEmail();
//         Users user = uow.users.jink().where(e -> e.getEmail().equals(email)).findFirst().get();

//         if (user.getPassword().equals(model.getPassword()) == false) {
//             // return ResponseEntity.ok(Map.of("code", -1));
//             return new ResponseEntity<>(Collections.singletonMap("code", -1), HttpStatus.CREATED);
//         } else {
//             Map<String, Object> claims = new HashMap<>();

//             claims.put("email", user.getEmail());
//             claims.put("role", user.getRole().toString());

//             String token = jwtTokenUtil.doGenerateToken(claims, user.getFirstname());

//             // Map<String, Object> response = Map.of("token", token, "user", user, "role",
//             // user.getRole().toString());

//             return ResponseEntity.ok(new JwtResponse(token,user));
//         }
//     }

//     @PostMapping("/register")
//     // @RequestMapping(method = RequestMethod.POST, value = ControllersUrls.REGISTER)
//     public ResponseEntity<?> register(@RequestBody Users model) {
//         Users user=null;
//         // String email = model.getEmail();
//         // Object o = uow.users.jink().where(e -> e.getEmail().equals(email)).select(e -> e).toList();

//         try {
//             // model.setPassword(bCryptPasswordEncoder.encode(model.getPassword()));
//             model.setPassword(model.getPassword());

//             user=uow.users.save(model);

//             // authenticate(user.getEmail(), user.getPassword());

//         } catch (Exception e) {
//             return ResponseEntity.ok(null);
//         }

//         Map<String, Object> claims = new HashMap<>();

//         claims.put("email", user.getEmail());
//         claims.put("role", user.getRole().toString());

//         String token = jwtTokenUtil.doGenerateToken(claims, user.getEmail());


//         // Map<String, Object> response = Map.of("token", token, "user", user);

//         return ResponseEntity.ok(new JwtResponse(token,user));

//     }

//     @GetMapping("/test/{id}")
//     public ResponseEntity<?> test(@PathVariable("id") Long id) {

//         String[] list = { "Volvo", "BMW", "Ford", "Mazda" };

//         try {
//             // Object o = uow.Practitioners.jink(Practitioner.class).where(e ->
//             // e.isActive()).select(e -> e.getFirstname())
//             // .toList();
//             return ResponseEntity.ok("");
//         } catch (Exception e) {
//             return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//         }
//     }

//     public class UserDTO {
//         public int id;
//         public String firstname;
//         public String email;
//         public String password;
//         public boolean active;
//         public String role;
//     }
// }