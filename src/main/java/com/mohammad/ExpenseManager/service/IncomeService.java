package com.mohammad.ExpenseManager.service;

import com.mohammad.ExpenseManager.dto.IncomeDto;
import com.mohammad.ExpenseManager.dto.IncomeResponseDto;

public interface IncomeService  {
    public IncomeResponseDto createIncome (IncomeDto incomeDto);
   // public List<IncomeResponseDto> getAllIncomeForCurrentUser();
   // public IncomeResponseDto getIncomeById(Long id);
   // public IncomeResponseDto updateIncome(Long id,IncomeDto incomeDto);
  //  public void deleteIncome(Long id);
}
