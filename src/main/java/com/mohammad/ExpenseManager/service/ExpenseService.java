package com.mohammad.ExpenseManager.service;

import com.mohammad.ExpenseManager.dto.ExpenseDto;
import com.mohammad.ExpenseManager.dto.ExpenseResponseDto;
import com.mohammad.ExpenseManager.model.ExpenseCategory;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseService {
    public ExpenseResponseDto createExpense(ExpenseDto expenseDto);
    public List<ExpenseResponseDto>getAllCurrentUserExpenses();
    public List<ExpenseResponseDto>getExpensesDateBetween(LocalDate startDate,LocalDate endDate);
    public List<ExpenseResponseDto> getExpensesOnDate(LocalDate date);
    public ExpenseResponseDto getExpenseById(Long id);
    public ExpenseResponseDto updateExpense(Long id ,ExpenseDto expenseDto);
    public boolean deleteExpense(Long id);
    public List<ExpenseResponseDto>getExpensesByCategory(ExpenseCategory category);
    public List<ExpenseResponseDto>getExpensesByAmountRange(Double minAmount,Double maxAmount);
    public List<ExpenseResponseDto> searchExpenses(String keyWord);
}
