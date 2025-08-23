package com.mohammad.ExpenseManager.dto;

import com.mohammad.ExpenseManager.model.ExpenseCategory;
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
public class ExpenseResponseDto {
    private  Long id;
    private String title;
    private BigDecimal amount;
    private LocalDate date;
    private ExpenseCategory category;
    private String description;
    private String userName;
}
