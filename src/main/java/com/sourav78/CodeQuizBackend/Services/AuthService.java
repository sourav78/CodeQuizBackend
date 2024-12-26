package com.sourav78.CodeQuizBackend.Services;

import com.sourav78.CodeQuizBackend.Entity.User;
import com.sourav78.CodeQuizBackend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepo userRepo;

    // Register user service
    public String register(User user){

        // Check if user already exists by username
        User existUserByUsername = userRepo.findByUserName(user.getUserName());
        if(existUserByUsername != null){
            // Return error message
            return "User already exists";
        }

        // Check if user already exists by email
        User existUserByEmail = userRepo.findByEmail(user.getEmail());
        if(existUserByEmail != null){
            // Return error message
            return "Email already exists";
        }

        // Save the user in DB
        userRepo.save(user);

        // Return success message
        return "User registered successfully";
    }
}
