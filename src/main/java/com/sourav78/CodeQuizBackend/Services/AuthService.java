package com.sourav78.CodeQuizBackend.Services;

import com.sourav78.CodeQuizBackend.Entity.User;
import com.sourav78.CodeQuizBackend.Exceptions.ResourceAlreadyExistException;
import com.sourav78.CodeQuizBackend.Repository.UserRepo;
import com.sourav78.CodeQuizBackend.Utils.OtpGenerator;
import com.sourav78.CodeQuizBackend.Utils.Validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
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

    // Verify user
    public void verifyUser(String email, String verificationCode) {
        //Find user by email
        User user = userRepo.findByEmail(email);
        if(user == null){
            throw new BadCredentialsException("User not found");
        }

        //Compare the verification code
        if(user.getVerificationCode() != null && user.getVerificationCode().equals(verificationCode)){
            user.setVerified(true);
            user.setVerificationCode(null);

            //Save the verification Status
            userRepo.save(user);
        }else{
            throw new BadCredentialsException("Invalid Verification Code");
        }
    }

    // Resend verification code
    public void resendVerificationCode(String email) {
        //Find user by email
        User user = userRepo.findByEmail(email);

        //Check if user exists
        if(user == null){
            throw new BadCredentialsException("User not found");
        }

        //Check if user is already verified
        if(user.isVerified()){
            throw new BadCredentialsException("User is already verified");
        }

        // Generate verification code
        user.setVerificationCode(OtpGenerator.generateOTP(6));
        userRepo.save(user);

        //Send a mail to the user with the verification code
        emailService.sendVerificationMail(user.getEmail(), "Verification Code", user.getVerificationCode());
    }

    // Forgot Password Verification API
    public void sendPasswordVerificationCode(String email){
        //Find user by email
        User user = userRepo.findByEmail(email);

        //Check if user exists
        if(user == null){
            throw new BadCredentialsException("Email is not found");
        }

        // Generate verification code
        user.setVerificationCode(OtpGenerator.generateOTP(6));
        userRepo.save(user);

        //Send a mail to the user with the verification code
        emailService.sendVerificationMail(user.getEmail(), "Verification Code", user.getVerificationCode());
    }

    // Reset Password service
    public void resetPassword(User user) {

        // Validate required fields
        UserValidator.validatePasswordReset(user);

        //Find user by email
        User userFromDB = userRepo.findByEmail(user.getEmail());

        //Check if user exists
        if(userFromDB == null){
            throw new BadCredentialsException("Email is not found");
        }

        //Compare the verification code
        if(userFromDB.getVerificationCode() != null && userFromDB.getVerificationCode().equals(user.getVerificationCode())){

            //Check if the new password is same as the old password
            boolean isPasswordSame = passwordEncoder.matches(user.getPassword(), userFromDB.getPassword());

            if(isPasswordSame){
                throw new BadCredentialsException("New password cannot be the same as the old password");
            }

            // Encode the password
            userFromDB.setPassword(passwordEncoder.encode(user.getPassword()));
            userFromDB.setVerificationCode(null);

            //Save the new password
            userRepo.save(userFromDB);
        }else{
            throw new BadCredentialsException("Invalid Verification Code");
        }
    }
}
