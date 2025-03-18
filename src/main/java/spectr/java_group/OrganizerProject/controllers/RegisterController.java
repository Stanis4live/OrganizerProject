package spectr.java_group.OrganizerProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import spectr.java_group.OrganizerProject.user.User;
import spectr.java_group.OrganizerProject.user.UserService;

import java.util.Map;

@RestController
//@CrossOrigin(origins = "http://localhost:8899")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, String> requestData) {
        String email = requestData.get("email");
        String password = requestData.get("password");
        if (userService.existsByEmail(email)) {
            return ResponseEntity.badRequest().body("Email already registered");
        }
        userService.addUser(new User(email, passwordEncoder.encode(password)));
        return ResponseEntity.ok("Registration success");
        }
}
