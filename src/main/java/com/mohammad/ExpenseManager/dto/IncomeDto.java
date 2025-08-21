package com.mohammad.ExpenseManager.dto;

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

public class IncomeDto {

    @NotBlank(message = "The Title is required")
    @Size(min = 3, max = 20, message = "the title should between 3-20 characters")
    private String title;

    @NotNull(message = "The amount is required")
    @Positive
    private BigDecimal amount;

    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate date;

    @Size(max = 200)
    private String description;
}
