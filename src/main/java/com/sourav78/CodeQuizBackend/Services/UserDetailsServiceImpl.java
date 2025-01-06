package com.sourav78.CodeQuizBackend.Services;

import com.sourav78.CodeQuizBackend.Entity.User;
import com.sourav78.CodeQuizBackend.Exceptions.ResourceNotFoundException;
import com.sourav78.CodeQuizBackend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws ResourceNotFoundException {
        User user = userRepo.findByUserName(username);
        if (user != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRoles() ? "ADMIN" : "USER")
                    .build();
        }
        throw new ResourceNotFoundException("User not found with username: " + username);
    }

    public UserDetails loadUserById(Long userId) throws ResourceNotFoundException {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRoles() ? "ADMIN" : "USER")
                    .build();
        }
        throw new ResourceNotFoundException("User not found");
    }

    // Get user id by username for JWT token
    public Long getUserIdByUsername(String username) throws ResourceNotFoundException {
        User user = userRepo.findByUserName(username);
        if (user != null) {
            return user.getId();
        }
        throw new ResourceNotFoundException("User not found with username: " + username);
    }
}
