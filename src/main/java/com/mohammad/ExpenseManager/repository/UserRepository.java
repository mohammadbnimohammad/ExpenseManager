package com.mohammad.ExpenseManager.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mohammad.ExpenseManager.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
}
