package com.sourav78.CodeQuizBackend.Utils.Validators;

import com.sourav78.CodeQuizBackend.Entity.User;
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
}
