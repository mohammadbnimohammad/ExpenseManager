package com.mohammad.ExpenseManager.service;

import com.mohammad.ExpenseManager.dto.UserDto;
import com.mohammad.ExpenseManager.dto.UserLoginDto;
import com.mohammad.ExpenseManager.dto.UserResponseDto;
import com.mohammad.ExpenseManager.model.User;

public interface UserService {
   public UserResponseDto createUser(UserDto userDto);

   public UserResponseDto userLogin (UserLoginDto userLoginDto);

   public UserResponseDto getUserById(Long id);
}
