package com.mohammad.ExpenseManager.service;

import com.mohammad.ExpenseManager.dto.IncomeDto;
import com.mohammad.ExpenseManager.dto.IncomeResponseDto;

import java.util.List;

public interface IncomeService {
    public IncomeResponseDto createIncome(IncomeDto incomeDto);

    public List<IncomeResponseDto> getAllIncomeForCurrentUser();
    // public IncomeResponseDto getIncomeById(Long id);
    // public IncomeResponseDto updateIncome(Long id,IncomeDto incomeDto);
    //  public void deleteIncome(Long id);
}