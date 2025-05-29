package com.mohammad.ExpenseManager.service;

import com.mohammad.ExpenseManager.dto.JwtResponseDto;
import com.mohammad.ExpenseManager.dto.UserDto;
import com.mohammad.ExpenseManager.dto.UserLoginDto;
import com.mohammad.ExpenseManager.dto.UserResponseDto;


public interface UserService {
   public UserResponseDto createUser(UserDto userDto);

   public JwtResponseDto userLogin (UserLoginDto userLoginDto);

   public UserResponseDto getUserById(Long id);
}
