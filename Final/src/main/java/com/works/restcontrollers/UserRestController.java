package com.works.restcontrollers;

import com.works.entities.Admin;
import com.works.entities.User;
import com.works.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@CrossOrigin
@RestController
@RequiredArgsConstructor

@RequestMapping("/user")
public class UserRestController {
    final UserService userService;
    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody User user){
        return userService.save(user);
    }
    @PostMapping("/login")
    public User login(@RequestBody User user){
        return userService.login(user.getEmail(), user.getPassword());
    }
    @GetMapping("/list")
    public ResponseEntity list(){
        return userService.list();
    }
}
