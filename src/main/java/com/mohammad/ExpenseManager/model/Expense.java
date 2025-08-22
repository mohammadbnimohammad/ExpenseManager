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
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "the title is required")
    @Size(min = 3, max = 100, message = "the title should be 3-100 char ")
    @Column(nullable = false)
    private String title;

    @NotNull(message = "the amount is required")
    @Column(nullable = false)
    @Positive
    private BigDecimal amount;

    @Column(nullable = false, name = "expense_date")
    @PastOrPresent(message = "Expense date cannot be in the future")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    @NotNull(message = "the category is required ")
    private ExpenseCategory category;

    @Size(max = 255)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
}
