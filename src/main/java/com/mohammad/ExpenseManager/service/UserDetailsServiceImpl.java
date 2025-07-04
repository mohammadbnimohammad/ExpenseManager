package com.mohammad.ExpenseManager.service;

import com.mohammad.ExpenseManager.model.User;
import com.mohammad.ExpenseManager.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.userdetails.User.withUsername;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)throws UsernameNotFoundException{
        User user= userRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("user not found with email : "+ email));

        return withUsername(user.getEmail()).password(user.getPassword())
                .authorities("USER").build();

    }
}
