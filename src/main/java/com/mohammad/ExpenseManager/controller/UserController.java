package com.mohammad.ExpenseManager.controller;

import com.mohammad.ExpenseManager.model.User;
import com.mohammad.ExpenseManager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
      User saveUser=userService.createUser(user);
      return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }
}
