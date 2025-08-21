package com.mohammad.ExpenseManager.controller;

import com.mohammad.ExpenseManager.dto.IncomeDto;
import com.mohammad.ExpenseManager.dto.IncomeResponseDto;
import com.mohammad.ExpenseManager.service.IncomeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incomes")
public class IncomeController {

    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService){
        this.incomeService=incomeService;
    }

    @PostMapping
    public ResponseEntity<IncomeResponseDto> createIncome(@Valid@RequestBody IncomeDto incomeDto){
        IncomeResponseDto saveIncome=incomeService.createIncome(incomeDto);
        return new ResponseEntity<>(saveIncome , HttpStatus.CREATED);
    }
    @GetMapping
    ResponseEntity<List<IncomeResponseDto>>getAllIncome(){
        List<IncomeResponseDto> getAllIncome=incomeService.getAllIncomeForCurrentUser();
        return new ResponseEntity<>(getAllIncome,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<IncomeResponseDto>getById(@PathVariable Long id){
        IncomeResponseDto getById=incomeService.getIncomeById(id);
        return new ResponseEntity<>(getById,HttpStatus.OK);
    }
    @PutMapping("/{id}")
   public ResponseEntity<IncomeResponseDto>updateIncome(@PathVariable Long id ,@Valid@RequestBody IncomeDto incomeDto){
      IncomeResponseDto update=incomeService.updateIncome(id,incomeDto);
      return new ResponseEntity<>(update,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteIncome(@PathVariable Long id){
        incomeService.deleteIncome(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}