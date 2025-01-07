package com.sourav78.CodeQuizBackend.Controllers;

import com.sourav78.CodeQuizBackend.Entity.UserInfo;
import com.sourav78.CodeQuizBackend.Services.UserService;
import com.sourav78.CodeQuizBackend.Utils.ResponseHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String hello(HttpServletRequest request) {
        long userId = (long) request.getAttribute("userId");
        return "Hello From user side" + userId;
    }

    // Get User Info for onboarding process
    @PostMapping("/info")
    public ResponseEntity<Object> getUserInfo(HttpServletRequest request, @RequestBody UserInfo userInfo) {
        long userId = (long) request.getAttribute("userId");

        // Set the user id in the user info object
        userInfo.setUserId(userId);

        // Save user info in DB
        UserInfo savedUserInfo = userService.saveUserInfo(userInfo);

        if(savedUserInfo == null){
            throw new RuntimeException("User Information Saving Failed");
        }

        return ResponseHandler.responseBuilder("User Information Saved Successfully", HttpStatus.OK, null);
    }
}
