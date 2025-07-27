package com.mohammad.ExpenseManager.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private  String username;
    @Email(message = "Email is must be valid ")
    private  String email;
    @NotBlank(message = "password is required")
    @Size(min = 8, max = 15, message = "Password must be between 8 to 15 characters")
    private  String password;


}
