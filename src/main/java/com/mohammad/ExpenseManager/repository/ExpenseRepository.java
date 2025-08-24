package com.mohammad.ExpenseManager.repository;

import com.mohammad.ExpenseManager.model.Expense;
import com.mohammad.ExpenseManager.model.ExpenseCategory;
import com.mohammad.ExpenseManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    public List<Expense> findByUser(User user);

    public List<Expense> findByUserAndCategory(User user, ExpenseCategory category);

    public List<Expense> findByUserAndDateBetween(User user, LocalDate startDate, LocalDate endDate);
}
