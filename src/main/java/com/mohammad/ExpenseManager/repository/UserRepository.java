package com.mohammad.ExpenseManager.repository;
import com.mohammad.ExpenseManager.dto.UserResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mohammad.ExpenseManager.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{
public boolean existsByUsername(String username);
public boolean existsByEmail(String email);
public Optional<User> findByEmail(String email);

}
