package com.mohammad.ExpenseManager.service;

import com.mohammad.ExpenseManager.dto.*;
import com.mohammad.ExpenseManager.exception.*;
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
    public UserResponseDto createUser(UserDto userDto) {


        // chack if username is not exists
        if (userRepository.existsByUsername(userDto.getUsername().trim())) {
            throw new UsernameAlreadyExistsException("user name is already taken. Please try another one.");
        }


        // check if email is not registered
        if (userRepository.existsByEmail(userDto.getEmail().trim())) {
            throw new EmailAlreadyExistsException("email already registered. Try logging in or use a different email.");
        }
        User user=new User();
        user.setUsername(userDto.getUsername().trim());
        user.setEmail(userDto.getEmail().trim());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        User saveUser=userRepository.save(user);
        return new UserResponseDto(saveUser.getId(),saveUser.getUsername(),saveUser.getEmail());
    }
}
