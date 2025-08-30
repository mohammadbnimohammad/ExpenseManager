package com.mohammad.ExpenseManager.service;

import com.mohammad.ExpenseManager.dto.ExpenseDto;
import com.mohammad.ExpenseManager.dto.ExpenseResponseDto;
import com.mohammad.ExpenseManager.exception.ExpenseNotFoundException;
import com.mohammad.ExpenseManager.exception.UnauthorizedAccessException;
import com.mohammad.ExpenseManager.model.Expense;
import com.mohammad.ExpenseManager.model.ExpenseCategory;
import com.mohammad.ExpenseManager.model.User;
import com.mohammad.ExpenseManager.repository.ExpenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserService userService;


    public ExpenseServiceImpl(ExpenseRepository expenseRepository, UserService userService) {
        this.expenseRepository = expenseRepository;
        this.userService = userService;
    }

    @Override
    public ExpenseResponseDto createExpense(ExpenseDto expenseDto) {
        User currentUser = userService.getCurrentEntity();

        Expense expense = new Expense();
        expense.setAmount(expenseDto.getAmount());
        if (expenseDto.getTitle() == null || expenseDto.getTitle().isBlank()) {
            throw new RuntimeException("Title must not be empty");
        }
        expense.setTitle(expenseDto.getTitle().trim());
        expense.setTitle(expenseDto.getTitle().trim());
        if (expenseDto.getDate() == null) {
            expense.setDate(LocalDate.now());
        } else {
            expense.setDate(expenseDto.getDate());
        }
        expense.setCategory(expenseDto.getCategory());
        if (expenseDto.getDescription()!=null){
            expense.setDescription(expenseDto.getDescription().trim());
        }
        expense.setUser(currentUser);

        Expense saveExpense = expenseRepository.save(expense);

        return new ExpenseResponseDto(
                saveExpense.getId(),
                saveExpense.getTitle(),
                saveExpense.getAmount(),
                saveExpense.getDate(),
                saveExpense.getCategory(),
                saveExpense.getDescription(),
                currentUser.getUsername()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExpenseResponseDto> getAllCurrentUserExpenses() {
        User currentUser = userService.getCurrentEntity();

        List<Expense> expenseList = expenseRepository.findByUser(currentUser);

        return expenseList.stream().map(
                expense -> new ExpenseResponseDto(
                        expense.getId(),
                        expense.getTitle(),
                        expense.getAmount(),
                        expense.getDate(),
                        expense.getCategory(),
                        expense.getDescription(),
                        currentUser.getUsername()
                )
        ).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ExpenseResponseDto getExpenseById(Long id) {
        User currentUser = userService.getCurrentEntity();

        Expense expense = expenseRepository.findById(id).orElseThrow(
                () -> new ExpenseNotFoundException("Expense not found with id " + id)
        );

        if (!expense.getUser().getId().equals(currentUser.getId())) {
            throw new UnauthorizedAccessException("You are not authorized to access this expense");


        }
        return new ExpenseResponseDto(
                expense.getId(),
                expense.getTitle(),
                expense.getAmount(),
                expense.getDate(),
                expense.getCategory(),
                expense.getDescription(),
                currentUser.getUsername()
        );
    }

    @Override
    @Transactional
    public ExpenseResponseDto updateExpense(Long id, ExpenseDto expenseDto) {
        User currentUser = userService.getCurrentEntity();

        Expense expense = expenseRepository.findById(id).orElseThrow(
                () -> new ExpenseNotFoundException("the expense is not existing")
        );

        if (!expense.getUser().getId().equals(currentUser.getId())) {
            throw new UnauthorizedAccessException("the Expense ID is not for current user");
        }
        expense.setAmount(expenseDto.getAmount());
        if (expenseDto.getTitle() == null || expenseDto.getTitle().isBlank()) {
            throw new RuntimeException("Title must not be empty");
        }
        expense.setTitle(expenseDto.getTitle().trim());
        expense.setTitle(expenseDto.getTitle().trim());
        if (expenseDto.getDate() == null) {
            expense.setDate(LocalDate.now());
        } else {
            expense.setDate(expenseDto.getDate());
        }
        expense.setCategory(expenseDto.getCategory());
        if (expenseDto.getDescription()!=null){
            expense.setDescription(expenseDto.getDescription().trim());
        }

        expenseRepository.save(expense);

        return new ExpenseResponseDto(
                expense.getId(),
                expense.getTitle(),
                expense.getAmount(),
                expense.getDate(),
                expense.getCategory(),
                expense.getDescription(),
                currentUser.getUsername()
        );
    }

    @Override
    public void deleteExpense(Long id) {
        User currentUser = userService.getCurrentEntity();


        Expense expense = expenseRepository.findById(id).
                orElseThrow(() -> new ExpenseNotFoundException("the expense is not existing"));

        if (!expense.getUser().getId().equals(currentUser.getId())) {
            throw new UnauthorizedAccessException("the Expense ID is not for current user");

        }
        expenseRepository.delete(expense);
    }

    @Override
    public List<ExpenseResponseDto> getExpensesDateBetween(LocalDate startDate, LocalDate endDate) {
        User currentUser = userService.getCurrentEntity();
        if (startDate==null||endDate==null){
            throw new RuntimeException("the start date should be not null");
        }
        if (startDate.isAfter(endDate)){
            throw new RuntimeException("the start date should be before end date ");
        }
        List<Expense> expenseList = expenseRepository.findByUserAndDateBetween(currentUser, startDate, endDate);

        return expenseList.stream().map(
                expense -> new ExpenseResponseDto(
                        expense.getId(),
                        expense.getTitle(),
                        expense.getAmount(),
                        expense.getDate(),
                        expense.getCategory(),
                        expense.getDescription(),
                        currentUser.getUsername()
                )
        ).toList();
    }

    @Override
    public List<ExpenseResponseDto> getExpensesOnDate(LocalDate date) {
        User currentUser = userService.getCurrentEntity();
         if (date==null){
             throw new RuntimeException("date must not be null");
         }
        List<Expense> expenseList = expenseRepository.findByUserAndDateBetween(currentUser, date, date);

        return expenseList.stream().map(
                expense -> new ExpenseResponseDto(
                        expense.getId(),
                        expense.getTitle(),
                        expense.getAmount(),
                        expense.getDate(),
                        expense.getCategory(),
                        expense.getDescription(),
                        currentUser.getUsername()
                )
        ).toList();
    }

    @Override
    public List<ExpenseResponseDto> getExpensesByCategory(ExpenseCategory category) {
        User currentUser = userService.getCurrentEntity();
        if (category==null){
            throw new RuntimeException("category must not be null");
        }
        List<Expense> expenseList = expenseRepository.findByUserAndCategory(currentUser, category);


        return expenseList.stream().map(
                expense -> new ExpenseResponseDto(
                        expense.getId(),
                        expense.getTitle(),
                        expense.getAmount(),
                        expense.getDate(),
                        expense.getCategory(),
                        expense.getDescription(),
                        currentUser.getUsername()
                )
        ).toList();
    }

    @Override
    public List<ExpenseResponseDto> getExpensesByAmountRange(BigDecimal minAmount, BigDecimal maxAmount) {
        User currentUser = userService.getCurrentEntity();
        if (minAmount==null||maxAmount==null||minAmount.compareTo(maxAmount)>=0){
            throw new RuntimeException("the min should be minimum than max ");
        }

        List<Expense> expenseList = expenseRepository.findByUserAndAmountBetween(currentUser, minAmount, maxAmount);
        return expenseList.stream().map(
                expense -> new ExpenseResponseDto(
                        expense.getId(),
                        expense.getTitle(),
                        expense.getAmount(),
                        expense.getDate(),
                        expense.getCategory(),
                        expense.getDescription(),
                        currentUser.getUsername()
                )
        ).toList();

    }

    @Override
    public List<ExpenseResponseDto> searchExpenses(String keyWord) {
        User currentUser = userService.getCurrentEntity();
        if (keyWord == null||keyWord.isBlank()) {
            throw new IllegalArgumentException("this filed must contain keyWord");
        }
        keyWord = keyWord.toLowerCase().trim();

        List<Expense> expenseList = expenseRepository.findByUserAndTitleKeyword(currentUser, keyWord);

        return expenseList.stream().map(
                expense -> new ExpenseResponseDto(
                        expense.getId(),
                        expense.getTitle(),
                        expense.getAmount(),
                        expense.getDate(),
                        expense.getCategory(),
                        expense.getDescription(),
                        currentUser.getUsername()
                )
        ).toList();

    }
}
