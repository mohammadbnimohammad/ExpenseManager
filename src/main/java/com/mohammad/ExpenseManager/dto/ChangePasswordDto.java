package com.mohammad.ExpenseManager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDto {
    @NotBlank(message = " current password is required")
    String currentPassword;
    @NotBlank(message = "new password is required")
    @Size(min = 8, max = 15, message = "Password must be between 8 to 15 characters")
    String newPassword;
}
