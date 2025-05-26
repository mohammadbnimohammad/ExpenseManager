package com.mohammad.ExpenseManager.service;

import com.mohammad.ExpenseManager.exception.EmailAlreadyExistsException;
import com.mohammad.ExpenseManager.exception.UsernameAlreadyExistsException;
import com.mohammad.ExpenseManager.model.User;
import com.mohammad.ExpenseManager.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // to save new user in app
    @Override
    public User createUser(User user) {

        String username = user.getUsername().trim();
        String email = user.getEmail().trim();

        // chack if username is not exists
        if (userRepository.existsByUsername(user.getUsername().trim())) {
            throw new UsernameAlreadyExistsException("user name is already taken. Please try another one.");
        }


        // check if email is not registered
        if (userRepository.existsByEmail(user.getEmail().trim())) {
            throw new EmailAlreadyExistsException("email already registered. Try logging in or use a different email.");
        }
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
