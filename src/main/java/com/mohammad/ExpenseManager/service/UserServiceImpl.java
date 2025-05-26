package com.mohammad.ExpenseManager.service;

import com.mohammad.ExpenseManager.exception.EmailAlreadyExistsException;
import com.mohammad.ExpenseManager.exception.UsernameAlreadyExistsException;
import com.mohammad.ExpenseManager.model.User;
import com.mohammad.ExpenseManager.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // to save new user in app
    @Override
    public User createUser(User user) {
         // check if username is empty
        if(user.getUsername()==null||user.getUsername().trim().isEmpty()){
            throw new UsernameAlreadyExistsException("user name must not be empty");
        }


        // chack if username is not exists
        if (userRepository.existsByUsername(user.getUsername().trim())) {
            throw new UsernameAlreadyExistsException("user name is already taken. Please try another one.");
        }

        // check if email is empty
        if (user.getEmail()==null||user.getEmail().trim().isEmpty()){
            throw new EmailAlreadyExistsException("email must not be empty");
        }
        // check if email is not registered
        if (userRepository.existsByEmail(user.getEmail().trim())) {
            throw new EmailAlreadyExistsException("email already registered. Try logging in or use a different email.");
        }
        // check if pass are valid
        if (user.getPassword()==null||user.getPassword().length() < 8 || user.getPassword().length() > 15) {
            throw new RuntimeException("password must be 8 to 15 character");
        }
        return userRepository.save(user);
    }
}
