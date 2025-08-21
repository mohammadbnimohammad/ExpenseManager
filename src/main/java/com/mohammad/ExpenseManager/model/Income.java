package com.mohammad.ExpenseManager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="income")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The title is required ")
    @Size(min = 3,max = 20,message = "the title should between 3-20 characters")
    @Column(nullable = false)
    private String title;


    @NotNull(message = "The amount is required")
    @Column(nullable = false)
    @Positive
    private BigDecimal amount;

    @Column(nullable = false,name = "income_date")
    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate date;

    @Size(max = 200 )
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
