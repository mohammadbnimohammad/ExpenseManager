package com.mohammad.ExpenseManager.controller;

import com.mohammad.ExpenseManager.dto.*;
import com.mohammad.ExpenseManager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/user")
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

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto>loginUser(@Valid@RequestBody UserLoginDto userLoginDto){
        JwtResponseDto loginUser=userService.userLogin(userLoginDto);
        return new ResponseEntity<>(loginUser,HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id){
        UserResponseDto getUserById=userService.getUserById(id);
        return new ResponseEntity<>(getUserById,HttpStatus.OK);
    }
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getCurrentUser(Authentication authentication) {
        String email = authentication.getName();
        UserResponseDto user = userService.getCurrentUser(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserResponseDto> updateUser(@Valid@RequestBody UpdateUserDto updateUserDto){
        UserResponseDto updateUser=userService.updateUser(updateUserDto);
        return ResponseEntity.ok(updateUser);
    }

}
