package com.example.springbootbasic.controller;

import com.example.springbootbasic.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class JavaClassApiController {

    //Text
    @GetMapping("/text")
    public String text(@RequestParam String account){
        return account;
    }

    //JSON
    @PostMapping("/json")
    public User json(@RequestBody User user){
        return user;
    }

    @PutMapping("/json")
    public ResponseEntity<User> put(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
