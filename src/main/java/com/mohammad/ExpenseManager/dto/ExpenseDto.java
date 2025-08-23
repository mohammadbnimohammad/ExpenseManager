package com.mohammad.ExpenseManager.dto;

import com.mohammad.ExpenseManager.model.ExpenseCategory;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDto {
    @NotBlank(message = "the title is required")
    @Size(min = 3, max = 100, message = "the title should be 3-100 char ")

    private String title;

    @NotNull(message = "the amount is required")
    @Positive
    private BigDecimal amount;

    @NotNull
    @PastOrPresent(message = "Expense date cannot be in the future")
    private LocalDate date;

    @NotNull(message = "the category is required ")
    private ExpenseCategory category;

    @Size(max = 255)
    private String description;
}
