package com.mohammad.ExpenseManager.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private long id;
    private String username;
    private  String email;
}
