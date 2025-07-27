package com.mohammad.ExpenseManager.dto;

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
public class IncomeResponseDto {
    private Long id;
    private String title;
    private BigDecimal amount;
    private LocalDate date;
    private String description;
}
