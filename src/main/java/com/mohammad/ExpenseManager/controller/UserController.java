package com.mohammad.ExpenseManager.controller;

import com.mohammad.ExpenseManager.dto.UserDto;
import com.mohammad.ExpenseManager.dto.UserResponseDto;
import com.mohammad.ExpenseManager.model.User;
import com.mohammad.ExpenseManager.service.UserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserDto userDto){
      UserResponseDto saveUser=userService.createUser(userDto);
      return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }
}
