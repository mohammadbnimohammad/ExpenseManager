package com.mohammad.ExpenseManager.repository;

import com.mohammad.ExpenseManager.model.Expense;
import com.mohammad.ExpenseManager.model.ExpenseCategory;
import com.mohammad.ExpenseManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    public List<Expense> findByUser(User user);

    public List<Expense> findByUserAndCategory(User user, ExpenseCategory category);

    public List<Expense> findByUserAndDateBetween(User user, LocalDate startDate, LocalDate endDate);

    public List<Expense> findByUserAndAmountBetween(User user, BigDecimal minAmount, BigDecimal maxAmount);

    @Query("SELECT e FROM Expense e WHERE e.user = :user AND LOWER(e.title) LIKE CONCAT('%', :kw, '%')")
    List<Expense> findByUserAndTitleKeyword(@Param("user") User user, @Param("kw") String kw);

}
