package com.mohammad.ExpenseManager.service;

import com.mohammad.ExpenseManager.dto.*;
import com.mohammad.ExpenseManager.model.User;


public interface UserService {
   public UserResponseDto createUser(UserDto userDto);

   public JwtResponseDto userLogin (UserLoginDto userLoginDto);

   public UserResponseDto getUserById(Long id);

   public UserResponseDto getCurrentUser(String email);

   public UserResponseDto updateUser(UpdateUserDto dto);

   public void changePassword(ChangePasswordDto changePasswordDto);

   public User getCurrentEntity();
}
