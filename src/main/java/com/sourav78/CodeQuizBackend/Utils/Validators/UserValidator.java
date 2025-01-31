package com.sourav78.CodeQuizBackend.Utils.Validators;

import com.sourav78.CodeQuizBackend.Entity.User;
import com.sourav78.CodeQuizBackend.Entity.UserInfo;
import com.sourav78.CodeQuizBackend.Exceptions.MissingFieldException;

public class UserValidator {
    public static void validateNewUser(User user){
        if(user.getUserName() == null || user.getUserName().isEmpty()){
            throw new MissingFieldException("Username cannot be empty");
        }
        if(user.getPassword() == null || user.getPassword().isEmpty()){
            throw new MissingFieldException("Password cannot be empty");
        }
        if(user.getEmail() == null || user.getEmail().isEmpty()){
            throw new MissingFieldException("Email cannot be empty");
        }
    }

    public static void validateUserInfo(UserInfo userInfo){
        if(userInfo.getFirstName() == null || userInfo.getFirstName().isEmpty()){
            throw new MissingFieldException("First Name cannot be empty");
        }
        if(userInfo.getBio() == null || userInfo.getBio().isEmpty()){
            throw new MissingFieldException("Please write something about yourself at least 10 words");
        }
        if(userInfo.getCountry() == null || userInfo.getCountry().isEmpty()){
            throw new MissingFieldException("Please Select your Country");
        }
    }

    public static void validateEmailVerification(User user){
        if(user.getEmail() == null || user.getEmail().isEmpty()){
            throw new MissingFieldException("Please provide email");
        }
        if(user.getVerificationCode() == null || user.getVerificationCode().isEmpty()){
            throw new MissingFieldException("Please provide verification code");
        }
        if(user.getVerificationCode().length() != 6){
            throw new MissingFieldException("Verification code must be 6 digits");
        }
    }

    public static void validatePasswordReset(User user){
        if(user.getEmail() == null || user.getEmail().isEmpty()){
            throw new MissingFieldException("Please provide email");
        }
        if(user.getVerificationCode() == null || user.getVerificationCode().isEmpty()){
            throw new MissingFieldException("Please provide verification code");
        }
        if(user.getVerificationCode().length() != 6){
            throw new MissingFieldException("Verification code must be 6 digits");
        }
        if(user.getPassword() == null || user.getPassword().isEmpty()){
            throw new MissingFieldException("Please provide new password");
        }
    }
}
