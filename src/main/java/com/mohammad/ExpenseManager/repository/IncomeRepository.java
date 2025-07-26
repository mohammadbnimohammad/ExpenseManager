package com.mohammad.ExpenseManager.repository;

import com.mohammad.ExpenseManager.model.Income;
import com.mohammad.ExpenseManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income,Long> {
List<Income>findByUser(User user);
}
