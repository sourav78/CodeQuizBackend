package com.sourav78.CodeQuizBackend.Services.Implementation;

import com.sourav78.CodeQuizBackend.Entity.UserInfo;
import com.sourav78.CodeQuizBackend.Repository.UserInfoRepo;
import com.sourav78.CodeQuizBackend.Services.UserService;
import com.sourav78.CodeQuizBackend.Utils.Validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Override
    public UserInfo saveUserInfo(UserInfo userInfo) {

        // Validate user info
        UserValidator.validateUserInfo(userInfo);

        // Save user info in DB
        UserInfo savedUserInfo = userInfoRepo.save(userInfo);

        return savedUserInfo;
    }
}
