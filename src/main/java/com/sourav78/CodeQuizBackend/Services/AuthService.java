package com.sourav78.CodeQuizBackend.Services;

import com.sourav78.CodeQuizBackend.Entity.User;
import com.sourav78.CodeQuizBackend.Exceptions.ResourceAlreadyExistException;
import com.sourav78.CodeQuizBackend.Repository.UserRepo;
import com.sourav78.CodeQuizBackend.Utils.Validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepo userRepo;

    //Password Encoder
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Register user service
    public String register(User user){

        // Check if user already exists by username
        User existUserByUsername = userRepo.findByUserName(user.getUserName());
        if(existUserByUsername != null){
            // Return error message
            throw new ResourceAlreadyExistException("Username is already taken.");
        }

        // Check if user already exists by email
        User existUserByEmail = userRepo.findByEmail(user.getEmail());
        if(existUserByEmail != null){
            // Return error message
            throw new ResourceAlreadyExistException("Email is already exist.");
        }

        // Validate required fields
        UserValidator.validateNewUser(user);

        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user in DB
        userRepo.save(user);

        // Return success message
        return "User registered successfully";
    }
}
