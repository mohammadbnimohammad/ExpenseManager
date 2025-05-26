package com.mohammad.ExpenseManager.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Column(nullable = false, unique = true)
    private String username;
    @NotBlank(message = "Email is required")
    @Email(message = "Email is must be valid ")
    @Column(nullable = false, unique = true)
    private String email;
    @NotBlank(message = "password is required")
    @Size(min = 8, max = 15, message = "Password must be between 8 and 15 characters")
    @Column(nullable = false ,name = "pass" )
    private String password;

}
