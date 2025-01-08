package com.sourav78.CodeQuizBackend.Services;

import com.sourav78.CodeQuizBackend.Entity.User;
import com.sourav78.CodeQuizBackend.Exceptions.ResourceAlreadyExistException;
import com.sourav78.CodeQuizBackend.Repository.UserRepo;
import com.sourav78.CodeQuizBackend.Utils.OtpGenerator;
import com.sourav78.CodeQuizBackend.Utils.Validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    private EmailService emailService;

    //Password Encoder
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //Check the user is verified or not
    public boolean isUserVerified(String username){
        User user = userRepo.findByUserName(username);
        return user.isVerified();
    }

    // Register user service
    public User register(User user) {

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

        // Generate verification code
        user.setVerificationCode(OtpGenerator.generateOTP(6));

        // Save the user in DB
        User savedUser = userRepo.save(user);

        //Send a mail to the user with the verification code
        emailService.sendVerificationMail(savedUser.getEmail(), "Verification Code", savedUser.getVerificationCode());

        // Return success message
        return savedUser;
    }
}
