package com.mohammad.ExpenseManager.service;

import com.mohammad.ExpenseManager.config.JwtUtil;
import com.mohammad.ExpenseManager.dto.*;
import com.mohammad.ExpenseManager.exception.*;
import com.mohammad.ExpenseManager.model.User;
import com.mohammad.ExpenseManager.repository.UserRepository;

import com.mohammad.ExpenseManager.security.CustomUserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;


    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtil = jwtUtil;
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
        User user = new User();
        user.setUsername(userDto.getUsername().trim());
        user.setEmail(userDto.getEmail().trim());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        User saveUser = userRepository.save(user);
        return new UserResponseDto(saveUser.getId(), saveUser.getUsername(), saveUser.getEmail());
    }

    @Override
    public JwtResponseDto userLogin(UserLoginDto userLoginDto) {
        User user = userRepository.findByEmail(userLoginDto.getEmail().trim())
                .orElseThrow(() -> new InvalidCredentialsException("email or password are invalid "));

        if (!bCryptPasswordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("email or password are invalid ");
        }

        JwtResponseDto jwtResponseDto=new JwtResponseDto();
        jwtResponseDto.setUsername(user.getUsername());
        jwtResponseDto.setEmail(user.getEmail());
        jwtResponseDto.setToken(jwtUtil.generateToken(new CustomUserDetails(user)));

        return jwtResponseDto;
    }

    @Override
    public UserResponseDto getUserById(Long id) {

        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id is not found"));

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setEmail(user.getEmail());

        return userResponseDto;
    }
}